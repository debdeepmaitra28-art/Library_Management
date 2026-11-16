import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
private val api=System.getenv("AURELIA_API")?:"http://localhost:8080/api"
private fun call(path:String,method:String="GET",body:String?=null):String{
 val c=URL("$api$path").openConnection() as HttpURLConnection;c.requestMethod=method;c.connectTimeout=3000;c.readTimeout=5000
 if(body!=null){c.doOutput=true;c.setRequestProperty("Content-Type","application/x-www-form-urlencoded");c.outputStream.use{it.write(body.toByteArray())}}
 val stream=if(c.responseCode<400)c.inputStream else c.errorStream
 val result=stream.bufferedReader().use{it.readText()};if(c.responseCode>=400)error("API ${c.responseCode}: $result");c.disconnect();return result
}
private fun enc(s:String)=URLEncoder.encode(s,Charsets.UTF_8)
fun main(){println("\n A U R E L I A / LIBRARIAN CONSOLE\n API: $api")
 while(true){println("\n 1 Catalogue\n 2 Active loans\n 3 Issue book\n 4 Return loan\n 5 Request a book\n 0 Exit");print(" Select: ")
 when(readLine()?.trim()){
 "1"->runCatching{println(call("/books"))}.onFailure{println(it.message)}
 "2"->runCatching{println(call("/loans"))}.onFailure{println(it.message)}
 "3"->{print(" Book ID: ");val id=readLine().orEmpty();runCatching{println(call("/loans","POST","bookId=${enc(id)}"))}.onFailure{println("Issue failed: ${it.message}")}}
 "4"->{print(" Loan ID: ");val id=readLine().orEmpty();runCatching{println(call("/loans/${enc(id)}","DELETE"))}.onFailure{println("Return failed: ${it.message}")}}
 "5"->{print(" Title: ");val t=readLine().orEmpty();print(" Author: ");val a=readLine().orEmpty();runCatching{println(call("/requests","POST","title=${enc(t)}&author=${enc(a)}"))}.onFailure{println("Request failed: ${it.message}")}}
 "0"->{println(" Until the next chapter.");return}
 else->println(" Choose a listed option.")
 }}
}