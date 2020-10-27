package model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/12
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class ResponseEntity {
    private int code;
    private String message;
    private Object data;

    public static ResponseEntity ok(){
        return new ResponseEntity(1,"Success",null);
    }
    public static ResponseEntity ok(Object data){
       return new ResponseEntity(1,"Success",data);
    }
    public static ResponseEntity ok(String message,Object data){
        return new ResponseEntity(1,message,data);
    }
    public static ResponseEntity ok(String message){
        return new ResponseEntity(1,message,null);
    }
    public static ResponseEntity fail(String message){
        return new ResponseEntity(0,message,null);
    }
    public static ResponseEntity fail(){
        return new ResponseEntity(0,"Error",null);
    }

}
