package com.xinhu.demo.controller;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;


public class Test {
    public static void main(String[] args) throws JSQLParserException {
        String sql = "SELECT * FROM mytable WHERE a = ?";
        Select select = (Select) CCJSqlParserUtil.parse(sql);


        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();

// 添加AND条件
        AndExpression and = new AndExpression(where, CCJSqlParserUtil.parseCondExpression("b = 2"));
        plainSelect.setWhere(and);

// 添加OR条件
        OrExpression or = new OrExpression(and, CCJSqlParserUtil.parseCondExpression("c = 3"));
        plainSelect.setWhere(or);

// 打印修改后的SQL
        String modifiedSql = select.toString();
        System.out.println(modifiedSql);
    }
}
