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
public class UpdateElementGenerator extends
        AbstractXmlElementGenerator {

    public UpdateElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("update"); //$NON-NLS-1$
        answer.addAttribute(new Attribute("id", "update")); //$NON-NLS-1$
        String parameterType;
        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }

        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();

        XmlElement dynamicElement = new XmlElement("foreach"); //$NON-NLS-1$
        dynamicElement.addAttribute(new Attribute("collection","list"));
        dynamicElement.addAttribute(new Attribute("item","item"));
        dynamicElement.addAttribute(new Attribute("index","index"));
        dynamicElement.addAttribute(new Attribute("separator",";"));
        dynamicElement.addAttribute(new Attribute("open",""));
        dynamicElement.addAttribute(new Attribute("close",""));
        answer.addElement(dynamicElement);
        sb.append("update "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        dynamicElement.addElement(new TextElement(sb.toString()));
        XmlElement set = new XmlElement("set"); //$NON-NLS-1$
        dynamicElement.addElement(set);
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getNonPrimaryKeyColumns()) {
            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
            sb.setLength(0);
            sb.append("item."+introspectedColumn.getJavaProperty());
            sb.append(" != null"); //$NON-NLS-1$
            if (introspectedColumn.getJdbcTypeName().equalsIgnoreCase("TINYINT")
                    ||introspectedColumn.getJdbcTypeName().equalsIgnoreCase("INTEGER")){
                sb.append(" and item."+introspectedColumn.getJavaProperty()+" !=  -1 ");
            }else if (introspectedColumn.getJdbcTypeName().equalsIgnoreCase("TIMESTAMP")){
                sb.append(" ");
            }else{
                sb.append(" and item."+introspectedColumn.getJavaProperty()+" !=  \'\'");
            }
            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
            set.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn).replaceAll("item.",""));
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            if (introspectedTable.getNonPrimaryKeyColumns().indexOf(introspectedColumn)!=introspectedTable.getNonPrimaryKeyColumns().size()-1){
                sb.append(',');
            }
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }

        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                sb.append("  and "); //$NON-NLS-1$
            } else {
                sb.append("where "); //$NON-NLS-1$
                and = true;
            }

            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn).replaceAll("item.",""));
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            dynamicElement.addElement(new TextElement(sb.toString()));
        }

        if (context.getPlugins()
                .sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
