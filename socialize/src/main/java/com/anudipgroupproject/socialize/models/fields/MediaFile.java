package com.anudipgroupproject.socialize.models.fields;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.hibernate.usertype.ParameterizedType;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Properties;



public class MediaFile implements UserType<File>, ParameterizedType {

	private static final String FILE_STORAGE_PATH = "src/main/resources/media/";
	private String subFolderName = "";
	
	@Override
    public void setParameterValues(Properties parameters) {
		String folderName = parameters.getProperty("folderName");
		this.subFolderName = (folderName != null ? folderName : "") + "/";
    }
	
    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<File> returnedClass() {
        return File.class;
    }

    @Override
    public boolean equals(File x, File y) {
        if (x == null || y == null) return false;
        return x == y || x.equals(y);
    }

    @Override
    public int hashCode(File f) {
    	return (f != null) ? f.hashCode() : 0;
    }
    
    @Override
    public File nullSafeGet(ResultSet resultSet, int position, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
    	String filePath = resultSet.getString(position);
        return (filePath != null) ? new File(filePath) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, File file, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (file != null) {
        	try {
                String fileName = file.getName();
                
                System.out.println(FILE_STORAGE_PATH + this.subFolderName);
                
                Files.createDirectories(Path.of(FILE_STORAGE_PATH + this.subFolderName));
                Path targetPath = Files.copy(file.toPath(), Path.of(FILE_STORAGE_PATH + this.subFolderName + fileName), StandardCopyOption.REPLACE_EXISTING);
                targetPath = targetPath.toAbsolutePath();
                preparedStatement.setString(index, targetPath.toString());
                
            } catch (IOException e) {
                // Handle the exception accordingly
            }
        } else {
            preparedStatement.setNull(index, Types.VARCHAR);
        }
    }

    @Override
    public File deepCopy(File file) {
    	return (file != null) ? new File(file.getAbsolutePath()) : null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

	@Override
	public Serializable disassemble(File f) {
		return f.getPath();
	}

    @Override
    public File assemble(Serializable cached, Object owner) {
        return (cached != null) ? new File((String) cached) : null;
    }

    @Override
    public File replace(File original, File target, Object owner) {
        return original;
    }
}