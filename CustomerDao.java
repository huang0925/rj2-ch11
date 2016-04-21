package com.briup.ch08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.briup.ch08.bean.Customer;
import com.briup.ch08.common.util.ConnectionFactory;
/**
 * 与数据库交互的类，不参与业务逻辑运算
 * save
 * find/query
 * update
 * delete
 * */
public class CustomerDao {

	/**
	 */
	public CustomerDao() {

	}

	/**
	 * @param customer
	 */
	public void save(Customer customer) {
		//6大步骤
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			try{
				//1.2 获取连接
				conn = ConnectionFactory.getConn();
				//3. 创建pstmt对象
				String sql = "insert into rj12_customer(name,password,age) "
						+ "values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, customer.getName());
				pstmt.setString(2, customer.getPassword());
				pstmt.setInt(3, customer.getAge());
				//4. 执行sql
				pstmt.executeUpdate();
				
			} finally {
				//6释放资源
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 */
	public void deleteById(long id) {

	}

	/**
	 * @param name
	 * @return com.briup.ch08.bean.Customer
	 */
	public Customer findByName(String name) {
		Customer customer = null;
		//6大步骤
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try{
				//1.2 获取连接
				conn = ConnectionFactory.getConn();
				//3. 创建pstmt对象
				String sql = "select * from rj12_customer where name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				//4. 执行sql
				rs = pstmt.executeQuery();
				while(rs.next()){
					long id = rs.getLong("id");
					String password = rs.getString("password");
					int age = rs.getInt("age");
					customer = new Customer(name, age, password);
					customer.setId(id);
				}
			} finally {
				//6释放资源
				ConnectionFactory.close(rs, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return customer;
	}
}
