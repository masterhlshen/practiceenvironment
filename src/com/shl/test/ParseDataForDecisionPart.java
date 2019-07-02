package com.shl.test;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author teacherKK
 */
public class ParseDataForDecisionPart {

    private Connection conn;

    public ParseDataForDecisionPart() {
        try {
            getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        // 找到oracle驱动器所在的类
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //URL地址
        String url = "jdbc:oracle:thin:@10.0.12.181:1521:oa";
        // get connection from server;
        this.conn = DriverManager.getConnection(url, "ezoffice", "oa1811");
    }

    public static void main(String[] args) {
        ParseDataForDecisionPart parseDataForDecisionPart = new ParseDataForDecisionPart();
        try {
            // eg:
            int i = parseDataForDecisionPart.updateSpecialField("9335", "9359", "资产管理部", "行政安保部");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            parseDataForDecisionPart.closeConnection();
        }

    }

    /**
     * 更新 特定 字段
     *
     * @param src     源
     * @param target  目标
     * @param src2    源2
     * @param target2 目标2
     * @return 1 - all successfully  -1 all failure
     */
    public int updateSpecialField(String src, String target, String src2, String target2) throws SQLException {
        List<NewFieldVO> targetList = new LinkedList<>();
        NewFieldVO vo = null;
        StringBuffer sql = new StringBuffer(100);
        sql.append("SELECT ody.id,ody.COOPERATEORG_NAME,ody.COOPERATEORG_ID,ody.REPORTORG_NAME,ody.REPORTORG_ID,ody.CREATE_ORGID ");
        sql.append(" FROM OA_DECISION_YTYS ODY ");
        sql.append(" WHERE ODY.CREATE_ORGID = ? OR ODY.REPORTORG_ID = ? OR ODY.COOPERATEORG_ID LIKE ? ");
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql.toString());
        preparedStatement.setObject(1, src);
        preparedStatement.setObject(2, src);
        preparedStatement.setObject(3, "%*" + src + "*%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int i = 0;
            vo = new NewFieldVO();
            vo.setDecisionId(resultSet.getLong(++i));
            // 资产管理部,法务部,;*9335**9355*  cooperatorgName
            String cooperatorgName = resultSet.getString(++i);
            //  *9335**9355* cooperatorgid
            String cooperatorgId = resultSet.getString(++i);
            if (cooperatorgId != null && !"".equals(cooperatorgId) && cooperatorgId.contains(("*" + src + "*"))) {
                vo.setCooperateId(cooperatorgId.replaceAll(src, target));
                // vo.setCooperateNameAndId(cooperatorgName.replaceAll(src, target).replaceAll(src2, target2));
                vo.setCooperateNameAndId(cooperatorgName.replaceAll(src, target));
            } else {
                vo.setCooperateId(cooperatorgId);
                vo.setCooperateNameAndId(cooperatorgName);
            }
            // 战略发展部;9331 reportorgname
            String reportOrgName = resultSet.getString(++i);
            // 9331 reportorgid
            String reportOrgId = resultSet.getString(++i);
            if (src.contains(reportOrgId)) {
                vo.setReportOrgId(target);
                // vo.setReportOrgNameAndId(reportOrgName.replaceAll(src, target).replace(src2, target2));
                vo.setReportOrgNameAndId(reportOrgName.replaceAll(src, target));
            } else {
                vo.setReportOrgId(reportOrgId);
                vo.setReportOrgNameAndId(reportOrgName);
            }
            // 9331 createorgid
            String createOrgId = resultSet.getString(++i);
            if (src.contains(createOrgId)) {
                vo.setCreateOrgId(target);
            } else {
                vo.setCreateOrgId(createOrgId);
            }
            targetList.add(vo);
        }
        if (targetList.size() > 0) {
            sql.setLength(0);
            sql.append("update OA_DECISION_YTYS ody set ody.COOPERATEORG_NAME = ?,ody.COOPERATEORG_ID = ?,ody.REPORTORG_NAME = ?," +
                    " ody.REPORTORG_ID = ?,ody.CREATE_ORGID = ?");
            sql.append(" where id = ? ");
            preparedStatement = this.conn.prepareStatement(sql.toString());
            this.conn.setAutoCommit(false);
            for (NewFieldVO aTargetList : targetList) {
                vo = aTargetList;
                int j = 0;
                preparedStatement.setObject(1, vo.getCooperateNameAndId());
                preparedStatement.setObject(++j, vo.getCooperateNameAndId());
                preparedStatement.setObject(++j, vo.getCooperateId());
                preparedStatement.setObject(++j, vo.getReportOrgNameAndId());
                preparedStatement.setObject(++j, vo.getReportOrgId());
                preparedStatement.setObject(++j, vo.getCreateOrgId());
                preparedStatement.setObject(++j, vo.getDecisionId());
                preparedStatement.executeUpdate();
            }
            // 提交
            this.conn.commit();
        }
        return 1;
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class NewFieldVO {
        /**
         * 议案主键
         */
        private long decisionId;
        /**
         * 配合和部门 名字 + id
         */
        private String cooperateNameAndId;
        /**
         * 配合部门 id
         */
        private String cooperateId;
        /**
         * 汇报部门 id
         */
        private String reportOrgId;
        /**
         * 汇报部门  名字 + id
         */
        private String reportOrgNameAndId;
        /**
         * 创建人 id
         */
        private String createOrgId;

        @Override
        public String toString() {
            return "1111111111";
        }

        long getDecisionId() {
            return decisionId;
        }

        void setDecisionId(long decisionId) {
            this.decisionId = decisionId;
        }

        String getCooperateNameAndId() {
            return cooperateNameAndId;
        }

        void setCooperateNameAndId(String cooperateNameAndId) {
            this.cooperateNameAndId = cooperateNameAndId;
        }

        String getCooperateId() {
            return cooperateId;
        }

        void setCooperateId(String cooperateId) {
            this.cooperateId = cooperateId;
        }

        String getReportOrgId() {
            return reportOrgId;
        }

        void setReportOrgId(String reportOrgId) {
            this.reportOrgId = reportOrgId;
        }

        String getReportOrgNameAndId() {
            return reportOrgNameAndId;
        }

        void setReportOrgNameAndId(String reportOrgNameAndId) {
            this.reportOrgNameAndId = reportOrgNameAndId;
        }

        String getCreateOrgId() {
            return createOrgId;
        }

        public void setCreateOrgId(String createOrgId) {
            this.createOrgId = createOrgId;
        }
    }

}
