package org.kuali.rice.devtools.jpa.eclipselink.conv.parser.helper.resolver;

import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.MemberValuePair;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NormalAnnotationExpr;
import japa.parser.ast.expr.QualifiedNameExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.metadata.DescriptorRepository;
import org.apache.ojb.broker.metadata.FieldDescriptor;
import org.kuali.rice.devtools.jpa.eclipselink.conv.ojb.OjbUtil;
import org.kuali.rice.devtools.jpa.eclipselink.conv.parser.helper.NodeData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ColumnResolver extends AbstractMappedFieldResolver {
    private static final Log LOG = LogFactory.getLog(ColumnResolver.class);

    public static final String PACKAGE = "javax.persistence";
    public static final String SIMPLE_NAME = "Column";

    public ColumnResolver(Collection<DescriptorRepository> descriptorRepositories) {
        super(descriptorRepositories);
    }

    @Override
    public String getFullyQualifiedName() {
        return PACKAGE + "." + SIMPLE_NAME;
    }

    @Override
    protected NodeData getAnnotationNodes(String enclosingClass, String fieldName, String mappedClass) {
        final FieldDescriptor fd = OjbUtil.findFieldDescriptor(mappedClass, fieldName, descriptorRepositories);

        if (fd != null) {
            List<MemberValuePair> pairs = new ArrayList<MemberValuePair>();
            final String access = fd.getAccess();
            if ("readonly".equals(access)) {
                pairs.add(new MemberValuePair("insertable", new BooleanLiteralExpr(false)));
                pairs.add(new MemberValuePair("updatable", new BooleanLiteralExpr(false)));
            } else if ("readwrite".equals(access)) {
                LOG.info(ResolverUtil.logMsgForField(enclosingClass, fieldName, mappedClass) + " field access is readwrite keeping @Column attributes (insertable, updatable) at defaults");
            } else if (access == null) {
                LOG.info(ResolverUtil.logMsgForField(enclosingClass, fieldName, mappedClass) + " field access is null keeping @Column attributes (insertable, updatable) at defaults");
            } else {
                LOG.error(ResolverUtil.logMsgForField(enclosingClass, fieldName, mappedClass) + " field access is " + access + ", unsupported conversion to @Column attributes");
            }

            final String columnName = fd.getColumnName();
            if (StringUtils.isNotBlank(columnName)) {
                pairs.add(new MemberValuePair("name", new StringLiteralExpr(columnName)));
            } else {
                LOG.error(ResolverUtil.logMsgForField(enclosingClass, fieldName, mappedClass) + " field column is blank");
            }

            final String columnType = fd.getColumnType();
            if (StringUtils.isNotBlank(columnType)) {
                LOG.error(enclosingClass + "." + fieldName + " for the mapped class " + mappedClass + " field column type is " + columnType + ", unsupported conversion to @Column attributes");
            }

            final boolean required = fd.isRequired();
            if (required) {
                pairs.add(new MemberValuePair("nullable", new BooleanLiteralExpr(false)));
            } else {
                LOG.info(ResolverUtil.logMsgForField(enclosingClass, fieldName, mappedClass) + " field is nullable keeping @Column attribute (nullable) at default");
            }

            final int length = fd.getLength();
            if (length > 0) {
                pairs.add(new MemberValuePair("length", new IntegerLiteralExpr(String.valueOf(length))));
            } else {
                LOG.info(ResolverUtil.logMsgForField(enclosingClass, fieldName, mappedClass) + " field length is not set keeping @Column attribute (length) at default");
            }

            final int precision = fd.getPrecision();
            if (precision > 0) {
                pairs.add(new MemberValuePair("precision", new IntegerLiteralExpr(String.valueOf(precision))));
            } else {
                LOG.info(ResolverUtil.logMsgForField(enclosingClass, fieldName, mappedClass) + " field precision is not set keeping @Column attribute (precision) at default");
            }

            final int scale = fd.getScale();
            if (scale > 0) {
                pairs.add(new MemberValuePair("scale", new IntegerLiteralExpr(String.valueOf(scale))));
            } else {
                LOG.info(ResolverUtil.logMsgForField(enclosingClass, fieldName, mappedClass) + " field scale is not set keeping @Column attribute (scale) at default");
            }
            return new NodeData(new NormalAnnotationExpr(new NameExpr(SIMPLE_NAME), pairs),
                    new ImportDeclaration(new QualifiedNameExpr(new NameExpr(PACKAGE), SIMPLE_NAME), false, false));
        }
        return null;
    }
}