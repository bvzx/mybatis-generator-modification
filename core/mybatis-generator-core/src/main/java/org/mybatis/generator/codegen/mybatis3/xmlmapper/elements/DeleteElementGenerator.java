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
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class DeleteElementGenerator extends
        AbstractXmlElementGenerator {


    public DeleteElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("delete"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", "delete")); //$NON-NLS-1$
        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                introspectedTable.getRules()
                        .calculateAllFieldsClass().getFullyQualifiedName()));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("delete from "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement dynamicElement = new XmlElement("trim");
        dynamicElement.addAttribute(new Attribute("prefix","where"));
        dynamicElement.addAttribute(new Attribute("prefixOverrides","and|or"));
        answer.addElement(dynamicElement);
        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getAllColumns()) {
            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null"); //$NON-NLS-1$
            if (introspectedColumn.getJdbcTypeName().equalsIgnoreCase("TINYINT")
                    ||introspectedColumn.getJdbcTypeName().equalsIgnoreCase("INTEGER")){
                sb.append(" and "+introspectedColumn.getJavaProperty()+" !=  -1 ");
            }else if (introspectedColumn.getJdbcTypeName().equalsIgnoreCase("TIMESTAMP")){
                sb.append(" ");
            }else{
                sb.append(" and "+introspectedColumn.getJavaProperty()+" !=  \'\'");
            }
            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
            dynamicElement.addElement(isNotNullElement);
            sb.setLength(0);
            if (!and){
                and=true;
            }else{
                sb.append("and ");
            }
            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn).replaceAll("item.",""));
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn).replaceAll("item.",""));
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
        parentElement.addElement(answer);
    }
}
