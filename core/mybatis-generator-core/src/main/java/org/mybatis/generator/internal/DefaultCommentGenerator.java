/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.internal;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * The Class DefaultCommentGenerator.
 *
 * @author Jeff Butler
 */
public class DefaultCommentGenerator implements CommentGenerator {

    /** The properties. */
    private Properties properties;
    
    /** The suppress date. */
    private boolean suppressDate;
    
    /** The suppress all comments. */
    private boolean suppressAllComments;

    /** The addition of table remark's comments.
     * If suppressAllComments is true, this option is ignored*/
    private boolean addRemarkComments;

    /**
     * Instantiates a new default comment generator.
     */
    public DefaultCommentGenerator() {
        super();
        properties = new Properties();
        suppressDate = false;
        suppressAllComments = false;
        addRemarkComments = false;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addJavaFileComment(org.mybatis.generator.api.dom.java.CompilationUnit)
     *
     */
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // add no file level comments by default
        if (compilationUnit instanceof InnerClass){
            InnerClass in=(InnerClass)compilationUnit;
            in.addJavaDocLine("/**");
            in.addJavaDocLine(" * 杭州动享互联网技术有限公司");
            in.addJavaDocLine(" * @ClassName: "+in.getType().getFullyQualifiedName());
            in.addJavaDocLine(" * @Author "+"dojoy");
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
            in.addJavaDocLine(" * @Date "+simpleDateFormat.format(new Date()));
            in.addJavaDocLine(" * @Description: TODO 这个类是做什么的");
            in.addJavaDocLine(" */");
        }
        if (compilationUnit instanceof Interface){
            Interface in=(Interface)compilationUnit;
            in.addJavaDocLine("/**");
            in.addJavaDocLine(" * 杭州动享互联网技术有限公司");
            in.addJavaDocLine(" * @ClassName: "+in.getType().getFullyQualifiedName());
            in.addJavaDocLine(" * @Author "+"dojoy");
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
            in.addJavaDocLine(" * @Date "+simpleDateFormat.format(new Date()));
            in.addJavaDocLine(" * @Description: TODO 这个类是做什么的");
            in.addJavaDocLine(" */");
        }
    }

    /**
     * Adds a suitable comment to warn users that the element was generated, and when it was generated.
     *
     * @param xmlElement
     *            the xml element
     */
    public void addComment(XmlElement xmlElement) {
        return;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addRootComment(org.mybatis.generator.api.dom.xml.XmlElement)
     */
    public void addRootComment(XmlElement rootElement) {
        // add no document level comments by default
        return;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addConfigurationProperties(java.util.Properties)
     */
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);

        suppressDate = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        
        suppressAllComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

        addRemarkComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
    }

    /**
     * This method adds the custom javadoc tag for. You may do nothing if you do not wish to include the Javadoc tag -
     * however, if you do not include the Javadoc tag then the Java merge capability of the eclipse plugin will break.
     *
     * @param javaElement
     *            the java element
     * @param markAsDoNotDelete
     *            the mark as do not delete
     */
    protected void addJavadocTag(JavaElement javaElement,
            boolean markAsDoNotDelete) {
    }

    /**
     * This method returns a formated date string to include in the Javadoc tag
     * and XML comments. You may return null if you do not want the date in
     * these documentation elements.
     * 
     * @return a string representing the current timestamp, or null
     */
    protected String getDateString() {
        if (suppressDate) {
            return null;
        } else {
            return new Date().toString();
        }
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addClassComment(org.mybatis.generator.api.dom.java.InnerClass, org.mybatis.generator.api.IntrospectedTable)
     */
    public void addClassComment(InnerClass innerClass,
            IntrospectedTable introspectedTable) {
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addTopLevelClassComment(org.mybatis.generator.api.dom.java.TopLevelClass, org.mybatis.generator.api.IntrospectedTable)
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addEnumComment(org.mybatis.generator.api.dom.java.InnerEnum, org.mybatis.generator.api.IntrospectedTable)
     */
    public void addEnumComment(InnerEnum innerEnum,
            IntrospectedTable introspectedTable) {
        return;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addFieldComment(org.mybatis.generator.api.dom.java.Field, org.mybatis.generator.api.IntrospectedTable, org.mybatis.generator.api.IntrospectedColumn)
     */
    public void addFieldComment(Field field,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        if (introspectedColumn.getRemarks()==null||introspectedColumn.getRemarks().equals("")){
            field.addJavaDocLine("/** NULL */"); //$NON-NLS-1$
        }else{
            field.addJavaDocLine("/** "+introspectedColumn.getRemarks()+" */"); //$NON-NLS-1$
        }
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addFieldComment(org.mybatis.generator.api.dom.java.Field, org.mybatis.generator.api.IntrospectedTable)
     */
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        return;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addGeneralMethodComment(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.IntrospectedTable)
     */
    public void addGeneralMethodComment(Method method,
            IntrospectedTable introspectedTable) {
        method.addJavaDocLine("/**");
        if (method.getName().equals("select")){
            method.addJavaDocLine(" * @param bundle "+introspectedTable.getRules()
                    .calculateAllFieldsClass().getFullyQualifiedName()+"实例作为查询条件 required");
            method.addJavaDocLine(" * @return 批量返回类型为"+introspectedTable.getRules()
                    .calculateAllFieldsClass().getFullyQualifiedName()+"的数据");
        }else if (method.getName().equals("update")){
            method.addJavaDocLine(" * @param bundle "+introspectedTable.getRules()
                    .calculateAllFieldsClass().getFullyQualifiedName()+"实例集合批量升级 required");
            method.addJavaDocLine(" * @return 返回更新条数");
        }else if (method.getName().equals("delete")){
            method.addJavaDocLine(" * @param bundle "+introspectedTable.getRules()
                    .calculateAllFieldsClass().getFullyQualifiedName()+"实例作为查询条件 required");
            method.addJavaDocLine(" * @return 批量返回删除条数");
        }else{
            method.addJavaDocLine(" * @param bundle "+introspectedTable.getRules()
                    .calculateAllFieldsClass().getFullyQualifiedName()+"实例集合批量插入 required");
            method.addJavaDocLine(" * @return 返回插入条数");
        }
        method.addJavaDocLine("*/");
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addGetterComment(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.IntrospectedTable, org.mybatis.generator.api.IntrospectedColumn)
     */
    public void addGetterComment(Method method,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        if (introspectedColumn.getRemarks()==null||introspectedColumn.getRemarks().equals("")){
            method.addJavaDocLine("/** NULL */"); //$NON-NLS-1$
        }else{
            method.addJavaDocLine("/** 获得"+introspectedColumn.getRemarks()+" */"); //$NON-NLS-1$
        }
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addSetterComment(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.IntrospectedTable, org.mybatis.generator.api.IntrospectedColumn)
     */
    public void addSetterComment(Method method,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        if (introspectedColumn.getRemarks()==null||introspectedColumn.getRemarks().equals("")){
            method.addJavaDocLine("/** NULL */"); //$NON-NLS-1$
        }else{
            method.addJavaDocLine("/** 设置"+introspectedColumn.getRemarks()+" */"); //$NON-NLS-1$
        }
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.CommentGenerator#addClassComment(org.mybatis.generator.api.dom.java.InnerClass, org.mybatis.generator.api.IntrospectedTable, boolean)
     */
    public void addClassComment(InnerClass innerClass,
            IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    }
}
