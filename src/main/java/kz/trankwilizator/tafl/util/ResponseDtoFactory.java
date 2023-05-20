package kz.trankwilizator.tafl.util;

import kz.trankwilizator.tafl.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;

public class ResponseDtoFactory {
    public static ResponseDto ok(String uri, Object body, String message){
        ResponseDto responseDto = withoutArgs();
        responseDto.setStatus(HttpStatus.OK.value());
        responseDto.setPath(uri);
        responseDto.setBody(body);
        responseDto.setMessage(message);
        return responseDto;
    }

    private static ResponseDto withoutArgs(){
        return new ResponseDto();
    }
}
