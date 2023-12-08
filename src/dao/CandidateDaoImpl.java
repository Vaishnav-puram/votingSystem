package dao;

import static utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Candidate;
import pojos.User;

public class CandidateDaoImpl implements CandidatesDao{
	
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3,pst4;

	// def ctor
	public CandidateDaoImpl() throws SQLException {
		// establish cn
		cn = openConnection();
		// pst1 : for displaying all candidates
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("select * from candidates where id=?");
		pst3=cn.prepareStatement("update candidates set votes=? where id=?");
		
		System.out.println("user dao created !");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		// TODO Auto-generated method stub
		List<Candidate> candidates=new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				// ORM : done by prog using JDBC
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getInt(4)));
		}
		return candidates;
	}

	@Override
	public Candidate getCandidate(int id) throws SQLException {
		// TODO Auto-generated method stub
		pst2.setInt(1,id);
		try(ResultSet rst=pst2.executeQuery()){
			if(rst.next()) {
				return new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getInt(4));
			}
		}
		return null;
	}
	@Override
	public String incrementVotes(int id) throws SQLException {
		// TODO Auto-generated method stub
		Candidate c=getCandidate(id);
		System.out.print(c);
		c.setVotes(c.getVotes()+1);
		pst3.setInt(1,c.getVotes());
		pst3.setInt(2,id);
		int rowCount=pst3.executeUpdate();
		if(rowCount==1) {
			return "vote added ";
		}
		return "vote could'nt be added!!!!!";
	}

	

}
