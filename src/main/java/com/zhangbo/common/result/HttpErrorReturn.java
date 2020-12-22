 package com.zhangbo.common.result;

 import java.io.IOException;
 import java.io.OutputStream;
 import org.springframework.beans.TypeMismatchException;


 public class HttpErrorReturn
 {
   public static void writeNoLogin(OutputStream outputStream)
     throws IOException
   {
     outputStream.write(new JsonResult(ResultCode.NOT_LOGIN).toString().getBytes("UTF-8"));
   }

   public static void writeLackAuthority(OutputStream outputStream) throws IOException {
       outputStream.write(new JsonResult(ResultCode.SYS_ERROR).toString().getBytes("UTF-8"));
   }

   public static void writeDataError(OutputStream outputStream, Exception ex) throws IOException {
       outputStream.write(new JsonResult(ResultCode.PARAMS_ERROR).toString().getBytes("UTF-8"));
   }

   public static String writeDataTypeMissingError(TypeMismatchException ex) {
       return new JsonResult(ResultCode.PARAMS_ERROR).toString();
   }
 }