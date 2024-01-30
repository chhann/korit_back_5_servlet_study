package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Publisher;




public class DBConnectionTestMain {
	public static void main(String[] args) {
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		
		
		try {//.next()d 다음이 있으면 true
			// getConnection -> 자바랑 데이터베이스 연결 도와줌
			con = pool.getConnection();
			String sql = "select * from author_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			List<Author> authorList = new ArrayList<>();

			
			//next( ) 커서이동
			while(rs.next()) {
//				System.out.println("id: " + rs.getInt(1));
//				System.out.println("name: " + rs.getString(2));
				authorList.add(Author.builder()
							.authorId(rs.getInt(1))
							.authorName(rs.getString(2))
							.build());				
			}
			
			
			// forEach
			authorList.forEach(author -> System.out.println(author));
			
			// 위에랑 똑같은 방법
//			for(Author author : authorList) {
//				System.out.println(author);
//			}
//			for(int i = 0; i < authorList.size(); i++) {
//				Author author = authorList.get(i);
//				System.out.println(author);
//			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		
		
	}
}
