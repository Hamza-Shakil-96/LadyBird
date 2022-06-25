package Services

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.*
import java.util.*


object FileServiceManager {
    fun getProps(filename: String = "data"): Properties {
        val myProps = Properties()
        try {
            val propFile = File("src/main/resources/$filename.properties")
            if (propFile.exists()) myProps.load(FileInputStream(propFile)) else println("FileServiceManager Not Found$filename")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return myProps
    }

    fun convertJavaObjectToJson(data: Any) {
        val mapper = ObjectMapper()
        val filePath = File(getProps().getProperty("json-File_Url"))
        try {
            val fw = FileWriter(filePath, false)
            val bw = BufferedWriter(fw)
            bw.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
            bw.close();
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun convertJsonToJavaObjects(): JsonNode {
        val mapper = ObjectMapper()
        return mapper.readTree(File(getProps().getProperty("json-File_Url")))
    }
}