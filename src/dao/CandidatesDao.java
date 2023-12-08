package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Candidate;

public interface CandidatesDao {
	Candidate getCandidate(int id)throws SQLException;
	List<Candidate> getAllCandidates()throws SQLException;
	String incrementVotes(int id)throws SQLException;
}
