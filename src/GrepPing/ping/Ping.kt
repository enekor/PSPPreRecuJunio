package GrepPing.ping

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter

object Ping {
    fun ping(pagina:String):String {

        val ping = Runtime.getRuntime().exec("wsl ping -c 4 $pagina")
        val pingReader = BufferedReader(InputStreamReader(ping.inputStream))

        var linea = pingReader.readLine()
        val lineas: MutableList<String> = ArrayList()
        while (linea  != null) {
            //println(linea)
            lineas.add(linea)
            linea= pingReader.readLine()
        }

        val grep = Runtime.getRuntime().exec("wsl grep mdev")
        val grepWriter = PrintWriter(OutputStreamWriter(grep.outputStream))
        lineas.forEach(grepWriter::println)
        grepWriter.close()

        val grepReader = BufferedReader(InputStreamReader(grep.inputStream))
        val ans = grepReader.readLine()

        if(ans == null){
            return "no hay resultados para $pagina"
        }else{
            val valores = ans.split("=")[1].split("/")
            return "Minimo: ${valores[0]}, Maximo: ${valores[2]}, Media: ${valores[1]}"
        }
    }
}