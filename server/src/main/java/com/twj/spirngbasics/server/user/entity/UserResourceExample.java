package com.twj.spirngbasics.server.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserResourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserResourceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPageIsNull() {
            addCriterion("page is null");
            return (Criteria) this;
        }

        public Criteria andPageIsNotNull() {
            addCriterion("page is not null");
            return (Criteria) this;
        }

        public Criteria andPageEqualTo(String value) {
            addCriterion("page =", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageNotEqualTo(String value) {
            addCriterion("page <>", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageGreaterThan(String value) {
            addCriterion("page >", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageGreaterThanOrEqualTo(String value) {
            addCriterion("page >=", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageLessThan(String value) {
            addCriterion("page <", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageLessThanOrEqualTo(String value) {
            addCriterion("page <=", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageLike(String value) {
            addCriterion("page like", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageNotLike(String value) {
            addCriterion("page not like", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageIn(List<String> values) {
            addCriterion("page in", values, "page");
            return (Criteria) this;
        }

        public Criteria andPageNotIn(List<String> values) {
            addCriterion("page not in", values, "page");
            return (Criteria) this;
        }

        public Criteria andPageBetween(String value1, String value2) {
            addCriterion("page between", value1, value2, "page");
            return (Criteria) this;
        }

        public Criteria andPageNotBetween(String value1, String value2) {
            addCriterion("page not between", value1, value2, "page");
            return (Criteria) this;
        }

        public Criteria andRequestIsNull() {
            addCriterion("request is null");
            return (Criteria) this;
        }

        public Criteria andRequestIsNotNull() {
            addCriterion("request is not null");
            return (Criteria) this;
        }

        public Criteria andRequestEqualTo(String value) {
            addCriterion("request =", value, "request");
            return (Criteria) this;
        }

        public Criteria andRequestNotEqualTo(String value) {
            addCriterion("request <>", value, "request");
            return (Criteria) this;
        }

        public Criteria andRequestGreaterThan(String value) {
            addCriterion("request >", value, "request");
            return (Criteria) this;
        }

        public Criteria andRequestGreaterThanOrEqualTo(String value) {
            addCriterion("request >=", value, "request");
            return (Criteria) this;
        }

        public Criteria andRequestLessThan(String value) {
            addCriterion("request <", value, "request");
            return (Criteria) this;
        }

        public Criteria andRequestLessThanOrEqualTo(String value) {
            addCriterion("request <=", value, "request");
            return (Criteria) this;
        }

        public Criteria andRequestLike(String value) {
            addCriterion("request like", value, "request");
            return (Criteria) this;
        }

        public Criteria andRequestNotLike(String value) {
            addCriterion("request not like", value, "request");
            return (Criteria) this;
        }

        public Criteria andRequestIn(List<String> values) {
            addCriterion("request in", values, "request");
            return (Criteria) this;
        }

        public Criteria andRequestNotIn(List<String> values) {
            addCriterion("request not in", values, "request");
            return (Criteria) this;
        }

        public Criteria andRequestBetween(String value1, String value2) {
            addCriterion("request between", value1, value2, "request");
            return (Criteria) this;
        }

        public Criteria andRequestNotBetween(String value1, String value2) {
            addCriterion("request not between", value1, value2, "request");
            return (Criteria) this;
        }

        public Criteria andParentIsNull() {
            addCriterion("parent is null");
            return (Criteria) this;
        }

        public Criteria andParentIsNotNull() {
            addCriterion("parent is not null");
            return (Criteria) this;
        }

        public Criteria andParentEqualTo(String value) {
            addCriterion("parent =", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotEqualTo(String value) {
            addCriterion("parent <>", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThan(String value) {
            addCriterion("parent >", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThanOrEqualTo(String value) {
            addCriterion("parent >=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThan(String value) {
            addCriterion("parent <", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThanOrEqualTo(String value) {
            addCriterion("parent <=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLike(String value) {
            addCriterion("parent like", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotLike(String value) {
            addCriterion("parent not like", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentIn(List<String> values) {
            addCriterion("parent in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotIn(List<String> values) {
            addCriterion("parent not in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentBetween(String value1, String value2) {
            addCriterion("parent between", value1, value2, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotBetween(String value1, String value2) {
            addCriterion("parent not between", value1, value2, "parent");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("created_by is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("created_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("created_by =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("created_by <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("created_by >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("created_by >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("created_by <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("created_by <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("created_by like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("created_by not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("created_by in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("created_by not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("created_by between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("created_by not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleIsNull() {
            addCriterion("dele is null");
            return (Criteria) this;
        }

        public Criteria andDeleIsNotNull() {
            addCriterion("dele is not null");
            return (Criteria) this;
        }

        public Criteria andDeleEqualTo(String value) {
            addCriterion("dele =", value, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleNotEqualTo(String value) {
            addCriterion("dele <>", value, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleGreaterThan(String value) {
            addCriterion("dele >", value, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleGreaterThanOrEqualTo(String value) {
            addCriterion("dele >=", value, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleLessThan(String value) {
            addCriterion("dele <", value, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleLessThanOrEqualTo(String value) {
            addCriterion("dele <=", value, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleLike(String value) {
            addCriterion("dele like", value, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleNotLike(String value) {
            addCriterion("dele not like", value, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleIn(List<String> values) {
            addCriterion("dele in", values, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleNotIn(List<String> values) {
            addCriterion("dele not in", values, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleBetween(String value1, String value2) {
            addCriterion("dele between", value1, value2, "dele");
            return (Criteria) this;
        }

        public Criteria andDeleNotBetween(String value1, String value2) {
            addCriterion("dele not between", value1, value2, "dele");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
