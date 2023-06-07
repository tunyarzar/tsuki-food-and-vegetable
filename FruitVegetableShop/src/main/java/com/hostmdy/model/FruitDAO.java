package com.hostmdy.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

public class FruitDAO {

	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public FruitDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int createFruit(Fruit fruit) {

		int rowUpdated = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection
					.prepareStatement("INSERT INTO `fruitdb`.`fruit` (`name`, `qty`, `date`, `price`, `cm`) VALUES (?,?,?,?,?);"
							+ ""
							);
			pStmt.setString(1, fruit.getName());
			pStmt.setInt(2, fruit.getQty());
			Date date = Date.valueOf(fruit.getDate());
			pStmt.setDate(3, date);
			pStmt.setDouble(4, fruit.getPrice());
			pStmt.setString(5, fruit.getCm());
			
			rowUpdated = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowUpdated;
	}

	public List<Fruit> getAllFruit() {
		List<Fruit> fruitList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = (ResultSet) stmt.executeQuery("select * from fruit;");

			while (rs.next()) {
				fruitList.add(new Fruit(rs.getInt("id"), rs.getString("name"), rs.getInt("qty"),

						LocalDate.parse(rs.getDate("date").toString()),rs.getDouble("price"),rs.getString("cm")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return fruitList;
	}

	public Fruit getFruitById(Integer id) {
		Fruit fruit = null;
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from fruit where id='" + id + "';");

			while (rs.next()) {
				fruit = new Fruit(rs.getInt("id"), rs.getString("name"), rs.getInt("qty"),LocalDate.parse(rs.getDate("date").toString()), rs.getDouble("price"),rs.getString("cm"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return fruit;
	}
	
	public int updateFruit (Fruit fruit) {
		int rowUpdated = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("UPDATE `fruit` SET"
					+ " `name` = ?, `qty` = ?, `date` = ?, `price` = ?, `cm` = ? "
					+ "WHERE (`id` = ?);");
			pStmt.setString(1, fruit.getName());
			pStmt.setDouble(2, fruit.getQty());
			Date date = Date.valueOf(fruit.getDate());
			pStmt.setDate(3, date);
			pStmt.setDouble(4, fruit.getPrice());
			pStmt.setString(5, fruit.getCm());
			pStmt.setInt(6, fruit.getId());
			
			rowUpdated = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowUpdated;
		
	}
	
	public int deleteFruit(Integer id) {
		int rowUpdated = 0;
		
		try {
			connection =dataSource.getConnection();
			
			pStmt = connection.prepareStatement("delete from fruit where id=?;");
			pStmt.setInt(1,id);
			rowUpdated = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowUpdated;
	}

	public int buyFruit(Integer id, Integer qty) {
        int rowUpdated = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("UPDATE `fruit` SET `qty` = ? WHERE (`id` = ?);");
			pStmt.setInt(1, qty);
			pStmt.setInt(2, id);		
			
			rowUpdated = pStmt.executeUpdate();
			System.out.println("Row Updated is"+rowUpdated);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowUpdated;
	}
	
}
