package com.edi.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 把表生成spring boot的bean
 * 
 * @author wengjunjie
 *
 */
public class MyEntity2Utils {
	private static String tablename = "";
	private static String tableComment = "";// 表注释
	private String[] colnames;// 字段名称
	private String[] colTypes;// 字段类型
	private String[] colComment;// 字段解释
	private int[] colSizes; // 列名大小
	private int[] colScale; // 列名小数精度
	private boolean importUtil = false;
	private boolean importSql = false;
	private boolean importMath = false;
	private static boolean ifWriteDomain = true;

	/**
	 * 根据表名字，生成bean对象
	 * 
	 * @param tName
	 * @throws SQLException
	 */
	public void tableToEntity(String tName) throws SQLException {
		getTableComment(tName);
		tablename = tName;
		// 数据连Connection获取,自己想办法就行.
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://172.16.0.39:3306/weishop", "root", "");
		String strsql = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '"
				+ tablename + "' AND table_schema = 'weishop';";// +" WHERE ROWNUM=1"
															// 读一行记录;
		try {
			System.out.println(strsql);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(strsql);
			// 处理结果
			int i = 0;
			rs.last();// 到最后一行
			int size = rs.getRow();
			colnames = new String[size];
			colTypes = new String[size];
			colComment = new String[size];
			colSizes = new int[size];
			colScale = new int[size];
			rs.beforeFirst();
			while (rs.next()) {
				// 每次循环，都取出一条记录
				colnames[i] = rs.getString("COLUMN_NAME");
				colTypes[i] = rs.getString("DATA_TYPE");
				colComment[i] = rs.getString("COLUMN_COMMENT");
				colSizes[i] = rs.getInt("CHARACTER_MAXIMUM_LENGTH");
				if ("datetime".equals(colTypes[i])) {
					importUtil = true;
				}
				if ("image".equals(colTypes[i]) || "text".equals(colTypes[i])) {
					importSql = true;
				}
				if (colScale[i] > 0) {
					importMath = true;
				}
				i++;
			}
			if(!ifWriteDomain){
				return;
			}
			String content = parse(colnames, colTypes, colSizes);
			// 把bean写入到指定目录
			try {
				String fileName = initcap(ColumnToPropertyUtil
						.columnToProperty(tablename)) + ".java";
				String workingDir = System.getProperty("user.dir");
				workingDir = workingDir
						+ "\\src\\main\\java\\com\\edi\\domain\\";
				FileWriter fw = new FileWriter(workingDir + fileName);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取表注释中文含义
	 * 
	 * @param tName
	 * @throws SQLException
	 */
	private void getTableComment(String tName) throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://172.16.0.39:3306/information_schema", "root", "");
		String strsql = "SELECT TABLE_COMMENT FROM tables WHERE table_name = '"
				+ tName + "'";// +" WHERE ROWNUM=1" 读一行记录;
		System.out.println(strsql);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(strsql);
		while (rs.next()) {
			tableComment = rs.getString("TABLE_COMMENT");
		}
		conn.close();
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		sb.append("package com.edi.domain;\r\n");
		sb.append("\r\nimport java.io.Serializable;");
		sb.append("\r\nimport javax.persistence.*;");
		sb.append("\r\nimport com.alibaba.fastjson.annotation.JSONField;\r\n");
		if (importUtil) {
			sb.append("import java.util.Date;\r\n");
		}
		if (importSql) {
			sb.append("import java.sql.*;\r\n\r\n");
		}
		if (importMath) {
			sb.append("import java.math.*;\r\n\r\n");
		}
		sb.append("\r\n/**");
		sb.append("\r\n * " + tableComment);
		sb.append("\r\n * @author jjweng@ibenben.com");
		sb.append("\r\n *");
		sb.append("\r\n */\r\n");
		sb.append("@Entity\r\n");
		sb.append("@Table(name=\"" + MyEntity2Utils.tablename + "\")\r\n");
		// 表注释
		// processColnames(sb);
		sb.append("public class "
				+ initcap(ColumnToPropertyUtil.columnToProperty(tablename))
				+ " implements Serializable {\r\n");
		processAllAttrs(sb);
		processAllMethod(sb);
		sb.append("}\r\n");
		System.out.println(sb.toString());
		return sb.toString();

	}

	/**
	 * 处理列名,把空格下划线'_'去掉,同时把下划线后的首字母大写 要是整个列在3个字符及以内,则去掉'_'后,不把"_"后首字母大写.
	 * 同时把数据库列名,列类型写到注释中以便查看,
	 * 
	 * @param sb
	 */
	private void processColnames(StringBuffer sb) {
		sb.append("\r\n/** " + tablename + "\r\n");
		String colsiz = "";
		String colsca = "";
		for (int i = 0; i < colnames.length; i++) {
			colsiz = colSizes[i] <= 0 ? "" : (colScale[i] <= 0 ? "("
					+ colSizes[i] + ")" : "(" + colSizes[i] + "," + colScale[i]
					+ ")");
			sb.append("\t" + colnames[i].toUpperCase() + "    "
					+ colTypes[i].toUpperCase() + colsiz + "\r\n");
			char[] ch = colnames[i].toCharArray();
			char c = 'a';
			if (ch.length > 3) {
				for (int j = 0; j < ch.length; j++) {
					c = ch[j];
					if (c == '_') {
						if (ch[j + 1] >= 'a' && ch[j + 1] <= 'z') {
							ch[j + 1] = (char) (ch[j + 1] - 32);
						}
					}
				}
			}
			String str = new String(ch);
			colnames[i] = str.replaceAll("_", "");
		}
		sb.append("*/\r\n");
	}

	/**
	 * 生成所有的方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			// 生成代码注释
			sb.append("\t/**\r\n");
			sb.append("\t * " + colComment[i] + "\r\n");
			sb.append("\t */\r\n");
			sb.append("\tpublic void set"
					+ initcap(ColumnToPropertyUtil
							.columnToProperty(colnames[i]))
					+ "("
					+ oracleSqlType2JavaType(colTypes[i], colScale[i],
							colSizes[i]) + " "
					+ ColumnToPropertyUtil.columnToProperty(colnames[i])
					+ "){\r\n");
			sb.append("\t\tthis."
					+ ColumnToPropertyUtil.columnToProperty(colnames[i]) + "="
					+ ColumnToPropertyUtil.columnToProperty(colnames[i])
					+ ";\r\n");
			sb.append("\t}\r\n");

			// 生成代码注释
			sb.append("\t/**\r\n");
			sb.append("\t * " + colComment[i] + "\r\n");
			sb.append("\t */\r\n");
			sb.append("\tpublic "
					+ oracleSqlType2JavaType(colTypes[i], colScale[i],
							colSizes[i])
					+ " get"
					+ initcap(ColumnToPropertyUtil
							.columnToProperty(colnames[i])) + "(){\r\n");
			sb.append("\t\treturn "
					+ ColumnToPropertyUtil.columnToProperty(colnames[i])
					+ ";\r\n");
			sb.append("\t}\r\n");
		}
	}

	/**
	 * 解析输出属性
	 * 
	 * @return
	 */
	private void processAllAttrs(StringBuffer sb) {
		Long UID = (long) (Math.random() * Math.pow(10, 19) + Math.pow(10, 10));
		sb.append("\tprivate static final long serialVersionUID = " + UID
				+ "L;\r\n");
		for (int i = 0; i < colnames.length; i++) {
			// 生成代码注释
			sb.append("\t/**\r\n");
			sb.append("\t * " + colComment[i] + "\r\n");
			sb.append("\t */\r\n");
			if (i == 0) {
				sb.append("\t@Id\r\n");
				sb.append("\t@GeneratedValue(strategy = GenerationType.IDENTITY)\r\n");
			}
			// sb.append("\t@JSONField  (name = \""+colnames[i]+"\")\r\n");
			sb.append("\t@Column  (name = \"" + colnames[i] + "\")\r\n");
			sb.append("\tprivate "
					+ oracleSqlType2JavaType(colTypes[i], colScale[i],
							colSizes[i]) + " "
					+ ColumnToPropertyUtil.columnToProperty(colnames[i])
					+ ";\r\n");
		}
		sb.append("\r\n");
	}

	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * Oracle
	 * 
	 * @param sqlType
	 * @param scale
	 * @return
	 */
	private String oracleSqlType2JavaType(String sqlType, int scale, int size) {
		if (sqlType.equals("integer") || sqlType.equals("int")
				|| sqlType.equals("tinyint") || sqlType.equals("smallint")|| sqlType.equals("mediumint")) {
			return "Integer";
		} else if (sqlType.equals("long") || sqlType.equals("bigint")) {
			return "Long";
		} else if (sqlType.equals("float") || sqlType.equals("float precision")
				|| sqlType.equals("double")
				|| sqlType.equals("double precision")) {
			return "BigDecimal";
		} else if (sqlType.equals("number") || sqlType.equals("decimal")
				|| sqlType.equals("numeric") || sqlType.equals("real")) {
			return scale == 0 ? (size < 10 ? "Integer" : "Long") : "BigDecimal";
		} else if (sqlType.equals("varchar") || sqlType.equals("varchar2")
				|| sqlType.equals("char") || sqlType.equals("nvarchar")
				|| sqlType.equals("text") || sqlType.equals("nchar")|| sqlType.equals("enum")) {
			return "String";
		} else if (sqlType.equals("datetime") || sqlType.equals("date")
				|| sqlType.equals("timestamp")) {
			return "Date";
		}
		return null;
	}

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		MyEntity2Utils t = new MyEntity2Utils();
		String table="temp_joke";
		//ifWriteDomain=false;//是否需要写bean
		t.tableToEntity(table);
		t.tableToMapper(table);
	}

	/**
	 * 根据表名字，生成Mappe的xml
	 * 
	 * @param string
	 */
	private void tableToMapper(String string) {
		String content = createTableToXml(colnames, colTypes, colSizes);
		// 把bean写入到指定目录
		try {
			String fileName = initcap(ColumnToPropertyUtil
					.columnToProperty(tablename)) + "Mapper.xml";
			String workingDir = System.getProperty("user.dir");
			workingDir = workingDir + "\\src\\main\\resources\\mapper\\";
			FileWriter fw = new FileWriter(workingDir + fileName);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(content);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 拿到table的字段名，返回配置好xml里面的值
	 * @param colnames2
	 * @param colTypes2
	 * @param colSizes2
	 * @return
	 */
	private String createTableToXml(String[] colnames, String[] colTypes,
			int[] colSizes) {
		String Tablename=initcap(ColumnToPropertyUtil.columnToProperty(tablename));
		String str="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n";
		str+="<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n";
		str+="<mapper namespace=\"com.edi.mapper."+Tablename+"Mapper\">\r\n";
		str+="\t<resultMap id=\"BaseResultMap\" type=\"com.edi.domain."+Tablename+"\">\r\n";
		//遍历所有字段
		for(int i=0;i<colnames.length;i++){
			if(i==0){
				str+="\t\t<id column=\"id\" jdbcType=\""+colTypes[i].toUpperCase()+"\" property=\"id\" />\r\n";
			}
			String jdbcType=colTypes[i].toUpperCase();
			if(jdbcType.equals("DATETIME")){
				jdbcType="TIMESTAMP";
			}
			if(jdbcType.equals("INT")){
				jdbcType="INTEGER";
			}
			if(jdbcType.equals("ENUM")){
				jdbcType="VARCHAR";
			}
			str+="\t\t<result column=\""+colnames[i]+"\" jdbcType=\""+jdbcType+"\" property=\""+ColumnToPropertyUtil.columnToProperty(colnames[i])+"\" />\r\n";
		}
		//遍历所有字段..end
		str+="\t</resultMap>\r\n";
		str+="</mapper>";
		return str;
	}
}
