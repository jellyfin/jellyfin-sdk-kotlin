package MediaBrowser.Model.Serialization;

public interface IXmlSerializer
{
	/** 
	 Deserializes from stream.
	 
	 @param type The type.
	 @param stream The stream.
	 @return System.Object.
	*/
	Object DeserializeFromStream(java.lang.Class type, Stream stream);

	/** 
	 Serializes to stream.
	 
	 @param obj The obj.
	 @param stream The stream.
	*/
	void SerializeToStream(Object obj, Stream stream);

	/** 
	 Serializes to file.
	 
	 @param obj The obj.
	 @param file The file.
	*/
	void SerializeToFile(Object obj, String file);

	/** 
	 Deserializes from file.
	 
	 @param type The type.
	 @param file The file.
	 @return System.Object.
	*/
	Object DeserializeFromFile(java.lang.Class type, String file);

	/** 
	 Deserializes from bytes.
	 
	 @param type The type.
	 @param buffer The buffer.
	 @return System.Object.
	*/
	Object DeserializeFromBytes(java.lang.Class type, byte[] buffer);

	/** 
	 Serializes to bytes.
	 
	 @param obj The obj.
	 @return System.Byte[][].
	*/
	byte[] SerializeToBytes(Object obj);
}