package cz.abdykili.lundegaard.controller;

import cz.abdykili.lundegaard.model.request.RequestTypeRequestDto;
import cz.abdykili.lundegaard.model.response.RequestTypeResponseDto;
import cz.abdykili.lundegaard.service.imp.RequestTypeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/request-types/")
@CrossOrigin
public class RequestTypeController {

    private final RequestTypeServiceImp requestTypeService;

    @PostMapping
    public RequestTypeResponseDto saveRequestType(@RequestBody RequestTypeRequestDto requestTypeRequestDto) {
        return requestTypeService.saveRequestType(requestTypeRequestDto);
    }

    @GetMapping
    public List<RequestTypeResponseDto> findAllRequestTypes() {
        return requestTypeService.findAllRequestTypes();
    }

    @GetMapping("{id}")
    public RequestTypeResponseDto findRequestType(@PathVariable Long id) {
        return requestTypeService.findRequestType(id);
    }
}
