package org.jellyfin.apiclient.serialization;

import java.io.InputStream;

public interface IJsonSerializer
{
	/**
	 Serializes to stream.

	 @param obj The obj.
	 @param stream The stream.
	 @exception System.ArgumentNullException obj
	*/
	void SerializeToStream(Object obj, InputStream stream);

	/**
	 Serializes to file.
	 
	 @param obj The obj.
	 @param file The file.
	 @exception System.ArgumentNullException obj
	*/
	void SerializeToFile(Object obj, String file);

	/** 
	 Deserializes from file.
	 
	 @param type The type.
	 @param file The file.
	 @return System.Object.
	 @exception System.ArgumentNullException type
	*/
	Object DeserializeFromFile(java.lang.Class type, String file);

	/** 
	 Deserializes from file.
	 
	 <typeparam name="T"></typeparam>
	 @param file The file.
	 @return ``0.
	 @exception System.ArgumentNullException file
	*/
	<T> T DeserializeFromFile(String file);

	/**
	 Deserializes from stream.

	 <typeparam name="T"></typeparam>
	 @param stream The stream.
	 @return ``0.
	 @exception System.ArgumentNullException stream
	*/
	<T> T DeserializeFromStream(InputStream stream);

	/**
	 Deserializes from string.
	 
	 <typeparam name="T"></typeparam>
	 @param text The text.
	 @return ``0.
	 @exception System.ArgumentNullException text
	*/
	<T> T DeserializeFromString(String text, java.lang.Class<T> type);

	/**
	 Deserializes from stream.

	 @param stream The stream.
	 @param type The type.
	 @return System.Object.
	 @exception System.ArgumentNullException stream
	*/
	Object DeserializeFromStream(InputStream stream, java.lang.Class type);

	/** 
	 Serializes to string.
	 
	 @param obj The obj.
	 @return System.String.
	 @exception System.ArgumentNullException obj
	*/
	String SerializeToString(Object obj);
}
