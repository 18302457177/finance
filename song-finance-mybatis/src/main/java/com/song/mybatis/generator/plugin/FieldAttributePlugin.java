package com.song.mybatis.generator.plugin;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.Collections;
import java.util.List;

/**
 * 字段属性插件
 */
public class FieldAttributePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        //获取表实体的名称
        String queryVoName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        //Java模型实体在哪个目标路径下
        String model = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        //是否设置了名称后缀没有以Field结尾
        String querVoSuffix = properties.getProperty("QUERVO_VO_SUFFIX", "Field");
        //构造生成类对象
        TopLevelClass root = new TopLevelClass(join(model, queryVoName + querVoSuffix));
//        root.addImportedType(model + "." + queryVoName);
        root.setVisibility(JavaVisibility.PUBLIC);
        root.addImportedType("com.song.mybatis.help.DbField");
        root.addImportedType("com.song.mybatis.help.FieldResult");
        root.addImportedType("java.util.Collections");
        //遍历所有字段
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : allColumns) {
            //大驼峰
            String varName = JavaBeansUtil.getCamelCaseString(column.getActualColumnName(), true);
            //小驼峰
            String attrName = JavaBeansUtil.getCamelCaseString(column.getActualColumnName(), false);
            //生成字段
            Field field = new Field(varName,
                    new FullyQualifiedJavaType("com.song.mybatis.help.DbField"));
            field.setVisibility(JavaVisibility.PUBLIC);
            field.setStatic(true);
            field.setInitializationString(String.format("new DbField(\"%s\",\"%s\",\"%s\",\"%s\")", column.getActualColumnName(), attrName, column.getJdbcTypeName(), column.getFullyQualifiedJavaType()));
            root.addField(field);

            Method setMethod = new Method("set" + field.getName());
            setMethod.setVisibility(JavaVisibility.PUBLIC);
            setMethod.setStatic(true);
            setMethod.addParameter(new Parameter(column.getFullyQualifiedJavaType(), attrName));
            setMethod.setReturnType(new FullyQualifiedJavaType("com.song.mybatis.help.FieldResult"));
            setMethod.addBodyLine("return new FieldResult(" + field.getName() + ", Collections.singletonList(" + attrName + "));");
            root.addMethod(setMethod);
        }

        String targetProject = context.getJavaClientGeneratorConfiguration().getTargetProject();
        GeneratedJavaFile gjf = new GeneratedJavaFile(root, targetProject, "UTF-8", new DefaultJavaFormatter());

        return Collections.singletonList(gjf);
    }

    private String join(String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str).append(".");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}