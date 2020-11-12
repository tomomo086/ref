package po.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import po.errors.DuplicateNameException;

public class DataAccessBean {


	// DataSourceを使うための記述
	private static DataSource ds = null;
	private static final String JNDI_NAME = "java:comp/env/jdbc/portforio";
	private static DataSource getDataSource() throws NamingException {

		//DataSource接続のキャッシュ処理
		if(ds == null) {

			//JNDIを使うための処理
			InitialContext ic = new InitialContext();

			//DataSourceと同じ型にキャスト
			ds = (DataSource) ic.lookup(JNDI_NAME);
		}
		return ds;
	}

	// 一覧表示機能・RefInfoをコレクション化する
	public Collection<RefInfo> findAllRefInfo() throws SQLException{

		//フィールド設定
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// SQLの命令文
			String sql = "select type,name,amount,buy,note from reizouko2 where gomi is null order by buy asc";

			// コレクションをインスタンス化したrefListを作成
			Collection<RefInfo> refList = new ArrayList<RefInfo>();

			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();


			while(rs.next()){
				RefInfo refInfo = new RefInfo();

				//refInfoオブジェクトにSQLから返された値をそれぞれセットする
				refInfo.setType(rs.getString("type"));
				refInfo.setName(rs.getString("name"));
				refInfo.setAmount(rs.getString("amount"));
				refInfo.setBuy(rs.getString("buy"));
				refInfo.setNote(rs.getString("note"));

				//refInfoオブジェクトをリストに追加
				refList.add(refInfo);
			}
			return refList;

		//例外処理
		} catch (NamingException e){
			e.printStackTrace();
			throw new SQLException(e);
		}

		//クローズ処理
		finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}


	// 賞味期限一覧表示機能・RefInfoをコレクション化する
	public Collection<RefInfo> findsyoumiRefInfo() throws SQLException{

		//フィールド設定
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// SQLの命令文
			String sql = "select type,name,amount,buy,note from reizouko2 "
					+ "where  gomi is null and note like '%期限%' order by buy asc";

			// コレクションをインスタンス化したrefListを作成
			Collection<RefInfo> refsyoumiList = new ArrayList<RefInfo>();

			conn = getDataSource().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();


			while(rs.next()){
				RefInfo refsyoumiInfo = new RefInfo();

				//refInfoオブジェクトにSQLから返された値をそれぞれセットする
				refsyoumiInfo.setType(rs.getString("type"));
				refsyoumiInfo.setName(rs.getString("name"));
				refsyoumiInfo.setAmount(rs.getString("amount"));
				refsyoumiInfo.setBuy(rs.getString("buy"));
				refsyoumiInfo.setNote(rs.getString("note"));

				//refInfoオブジェクトをリストに追加
				refsyoumiList.add(refsyoumiInfo);
			}
			return refsyoumiList;

		//例外処理
		} catch (NamingException e){
			e.printStackTrace();
			throw new SQLException(e);
		}

		//クローズ処理
		finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

	// ゴミ箱表示機能・RefInfoをコレクション化する
		public Collection<RefInfo> findgomiRefInfo() throws SQLException{

			//フィールド設定
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {

				// SQLの命令文
				String sql = "select type,name,amount,buy,note from reizouko2 "
						+ "where gomi = 1 order by buy asc";

				// コレクションをインスタンス化したrefListを作成
				Collection<RefInfo> refgomiList = new ArrayList<RefInfo>();

				conn = getDataSource().getConnection();
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();


				while(rs.next()){
					RefInfo refgomiInfo = new RefInfo();

					//refgomiInfoオブジェクトにSQLから返された値をそれぞれセットする
					refgomiInfo.setType(rs.getString("type"));
					refgomiInfo.setName(rs.getString("name"));
					refgomiInfo.setAmount(rs.getString("amount"));
					refgomiInfo.setBuy(rs.getString("buy"));
					refgomiInfo.setNote(rs.getString("note"));

					//refgomiInfoオブジェクトをリストに追加
					refgomiList.add(refgomiInfo);
				}
				return refgomiList;

			//例外処理
			} catch (NamingException e){
				e.printStackTrace();
				throw new SQLException(e);
			}

			//クローズ処理
			finally {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			}

		}



    //二重登録チェックのメソッド
	public void registRefInfo(RefInfo refInfo) throws SQLException,DuplicateNameException{

		//フィールド設定
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;

		try {

			//新規登録・SQL文
			String sql = "insert into reizouko2 (type,name,amount,buy,note,gomi) values(?,?,?,?,?,?)";

			//二重チェック・SQL文
			String sqlcheck = "select name from reizouko where name = ?";

			//DataSourceへコネクションする
			conn = getDataSource().getConnection();

			//二重チェック・オブジェクト生成
			ps1 = conn.prepareStatement(sqlcheck);

			//二重チェック・パラメーター設定
			ps1.setString(1, refInfo.getName());

			//二重チェック・検索
			rs = ps1.executeQuery();
			if (rs.next()) {
				throw new DuplicateNameException();
			}

			//新規登録・オブジェクト生成
			ps2 = conn.prepareStatement(sql);

			//新規登録・パラメーター設定
			ps2.setString(1, refInfo.getType());
			ps2.setString(2, refInfo.getName());
			ps2.setString(3, refInfo.getAmount());
			ps2.setString(4, refInfo.getBuy());
			ps2.setString(5, refInfo.getNote());
			ps2.setString(6, refInfo.getGomi());


			//新規登録SQL実行
			ps2.executeUpdate();


			//例外処理
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);

			//クローズ処理
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (ps2 != null) {
				ps2.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}


	//ゴミ箱移動機能のメソッド
		public void movegomiRefInfo(String name) throws SQLException{

			//フィールド設定
			Connection conn = null;
			PreparedStatement ps = null;

			try {

				//ゴミ箱移動機能・SQL文(gomiカラムに1の値をセットする)
				String sql = "update reizouko2 set gomi = 1 where name = ?";

				//DataSourceへコネクションする
				conn = getDataSource().getConnection();

    			//ゴミ箱・オブジェクト生成
				ps = conn.prepareStatement(sql);

				//ゴミ箱・パラメーター設定
				ps.setString(1, name);


				//ゴミ箱移動機能SQL実行
				ps.executeUpdate();


				//例外処理
			} catch (NamingException e) {
				e.printStackTrace();
				throw new SQLException(e);

				//クローズ処理
			} finally {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
			}
		}


	//削除機能・メソッド
	public void deleteRefInfo(String name) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			//削除用SQL実行文
			String sql = "delete from reizouko2 where name = ?";

			//SQLへのコネクション
			conn = getDataSource().getConnection();

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			//SQLの実行
			ps.executeUpdate();

		//例外処理
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);

			//クローズ処理
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
	}


	//復元機能のメソッド
			public void recoveryRefInfo(String name) throws SQLException{

				//フィールド設定
				Connection conn = null;
				PreparedStatement ps = null;

				try {

					//ゴミ箱移動機能・SQL文(gomiカラムに1の値をセットする)
					String sql = "update reizouko2 set gomi = null where name = ?";

					//DataSourceへコネクションする
					conn = getDataSource().getConnection();

	    			//ゴミ箱・オブジェクト生成
					ps = conn.prepareStatement(sql);

					//ゴミ箱・パラメーター設定
					ps.setString(1, name);


					//ゴミ箱移動機能SQL実行
					ps.executeUpdate();


					//例外処理
				} catch (NamingException e) {
					e.printStackTrace();
					throw new SQLException(e);

					//クローズ処理
				} finally {
					if (conn != null) {
						conn.close();
					}
					if (ps != null) {
						ps.close();
					}
				}
			}

}
