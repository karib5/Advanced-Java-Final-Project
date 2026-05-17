package com.eliteperformance.repository;

import com.eliteperformance.model.BonusRecord;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BonusRecordRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BonusRecordRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(BonusRecord bonusRecord) {
        String sql = "INSERT INTO bonus_records (employee_id, review_year, total_kpi_score, category, " +
                "bonus_percentage, bonus_amount, total_compensation) " +
                "VALUES (:employeeId, :reviewYear, :totalKpiScore, :category, " +
                ":bonusPercentage, :bonusAmount, :totalCompensation)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("employeeId", bonusRecord.getEmployeeId());
        params.addValue("reviewYear", bonusRecord.getReviewYear());
        params.addValue("totalKpiScore", bonusRecord.getTotalKpiScore());
        params.addValue("category", bonusRecord.getCategory());
        params.addValue("bonusPercentage", bonusRecord.getBonusPercentage());
        params.addValue("bonusAmount", bonusRecord.getBonusAmount());
        params.addValue("totalCompensation", bonusRecord.getTotalCompensation());

        jdbcTemplate.update(sql, params);
    }

    public List<BonusRecord> findByEmployeeId(Long employeeId) {
        String sql = "SELECT * FROM bonus_records WHERE employee_id = :employeeId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("employeeId", employeeId);

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            BonusRecord record = new BonusRecord();
            record.setBonusId(rs.getLong("bonus_id"));
            record.setEmployeeId(rs.getLong("employee_id"));
            record.setReviewYear(rs.getInt("review_year"));
            record.setTotalKpiScore(rs.getBigDecimal("total_kpi_score"));
            record.setCategory(rs.getString("category"));
            record.setBonusPercentage(rs.getBigDecimal("bonus_percentage"));
            record.setBonusAmount(rs.getBigDecimal("bonus_amount"));
            record.setTotalCompensation(rs.getBigDecimal("total_compensation"));
            return record;
        });
    }

    public List<BonusRecord> findAll() {
        String sql = "SELECT * FROM bonus_records";
        MapSqlParameterSource params = new MapSqlParameterSource();

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            BonusRecord record = new BonusRecord();
            record.setBonusId(rs.getLong("bonus_id"));
            record.setEmployeeId(rs.getLong("employee_id"));
            record.setReviewYear(rs.getInt("review_year"));
            record.setTotalKpiScore(rs.getBigDecimal("total_kpi_score"));
            record.setCategory(rs.getString("category"));
            record.setBonusPercentage(rs.getBigDecimal("bonus_percentage"));
            record.setBonusAmount(rs.getBigDecimal("bonus_amount"));
            record.setTotalCompensation(rs.getBigDecimal("total_compensation"));
            return record;
        });
    }
}