package com.eliteperformance.repository;

import com.eliteperformance.model.PerformanceReview;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceReviewRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PerformanceReviewRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsByEmployeeIdAndYear(Long employeeId, Integer reviewYear) {
        String sql = "SELECT COUNT(*) FROM performance_reviews WHERE employee_id = :employeeId AND review_year = :reviewYear";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("employeeId", employeeId);
        params.addValue("reviewYear", reviewYear);
        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

    public void save(PerformanceReview review) {
        String sql = "INSERT INTO performance_reviews (employee_id, review_year, task_completion, attendance, " +
                "team_collaboration, problem_solving, communication, leadership, client_satisfaction, total_kpi_score) " +
                "VALUES (:employeeId, :reviewYear, :taskCompletion, :attendance, :teamCollaboration, " +
                ":problemSolving, :communication, :leadership, :clientSatisfaction, :totalKpiScore)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("employeeId", review.getEmployeeId());
        params.addValue("reviewYear", review.getReviewYear());
        params.addValue("taskCompletion", review.getTaskCompletion());
        params.addValue("attendance", review.getAttendance());
        params.addValue("teamCollaboration", review.getTeamCollaboration());
        params.addValue("problemSolving", review.getProblemSolving());
        params.addValue("communication", review.getCommunication());
        params.addValue("leadership", review.getLeadership());
        params.addValue("clientSatisfaction", review.getClientSatisfaction());
        params.addValue("totalKpiScore", review.getTotalKpiScore());

        jdbcTemplate.update(sql, params);
    }
}