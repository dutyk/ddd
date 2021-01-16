package io.kang.bank.tools;

import com.itranswarp.warpdb.WarpDb;

import java.io.*;
import java.util.Arrays;

public class SchemaBuilder {
    public SchemaBuilder() {
    }

    public static void export(String basePackage, String dbName, File ddlFile, String charset) throws IOException {
        WarpDb db = new WarpDb();
        db.setBasePackages(Arrays.asList(basePackage));
        db.init();
        String schema = db.getDDL().replace(" BIT ", " BOOL ").replace(");\n", ") Engine=INNODB AUTO_INCREMENT=100000\n");
        StringBuilder sb = new StringBuilder(4096);
        sb.append("\n\n-- BEGIN generate DDL --\n\n");
        sb.append(String.format("DROP DATABASE IF EXISTS %s;\n\n", dbName));
        sb.append(String.format("CREATE DATABASE %s;\n\n", dbName));
        sb.append(String.format("DROP USER IF EXISTS %s_rw@'%%';\n\n", dbName));
        sb.append(String.format("CREATE USER %s_rw@'%%' IDENTIFIED BY '%s_rw_password';\n\n", dbName, dbName));
        sb.append(String.format("GRANT SELECT,INSERT,DELETE,UPDATE ON %s.* TO %s_rw@'%%' WITH GRANT OPTION;\n\n", dbName, dbName));
        sb.append(String.format("DROP USER IF EXISTS %s_ro@'%%';\n\n", dbName));
        sb.append(String.format("CREATE USER %s_ro@'%%' IDENTIFIED BY '%s_ro_password';\n\n", dbName, dbName));
        sb.append(String.format("GRANT SELECT ON %s.* TO %s_ro@'%%' WITH GRANT OPTION;\n\n", dbName, dbName));
        sb.append(String.format("USE %s;\n\n", dbName));
        sb.append(schema);
        sb.append("\n-- END generate DDL --\n");
        String sql = sb.toString();
        System.out.println(sql);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ddlFile), "UTF-8"));
        Throwable th = null;

        try {
            writer.write(sql);
        } catch (Throwable e) {
            th = e;
            throw e;
        } finally {
            if (writer != null) {
                if (th != null) {
                    try {
                        writer.close();
                    } catch (Throwable var17) {
                        th.addSuppressed(var17);
                    }
                } else {
                    writer.close();
                }
            }

        }

        System.out.println("Saved in " + ddlFile.getAbsolutePath());
        System.out.println("Run:\n\nmysql -u root --password=password < " + ddlFile.getAbsolutePath() + "\n");
    }

    public static void main(String[] args)throws IOException {
        export("io.kang.bank.tools.entity", "springcloud", new File("account.sql"), "UTF-8");
    }
}
