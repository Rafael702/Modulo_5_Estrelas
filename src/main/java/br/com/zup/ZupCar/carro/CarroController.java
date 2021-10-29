package br.com.zup.ZupCar.carro;

import br.com.zup.ZupCar.carro.dtos.CarroDTO;
import br.com.zup.ZupCar.carro.dtos.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<CarroDTO> exibirTodosOsCarros() {
        return carroService.pegarTodosOsCarros();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCarro(@RequestBody CarroDTO carroDTO) {
        carroService.salvarCarro(carroDTO);
    }

//    @GetMapping("/{nomeDoCarro}")
//    public CarroDTO exibirCarro(@PathVariable String nomeDoCarro){
//        System.out.println(nomeDoCarro);
//        return new CarroDTO();
//    }

//    @GetMapping("/{nomeDoCarro}")
//    public List<CarroDTO> pesquisarCarroNaLista(@PathVariable String nomeDoCarro) {
//       List<CarroDTO> pesquisarCarro = new ArrayList<>();
//
//       for(CarroDTO referenciaCarro : concessionaria){
//           if(referenciaCarro.getNome().contains(nomeDoCarro)){
//               pesquisarCarro = new ArrayList<>();
//               pesquisarCarro.add(referenciaCarro);
//           }
//       }
//       return pesquisarCarro;
//    }

    @GetMapping("/{nomeDoCarro}")
    public CarroDTO exibirCarro(@PathVariable String nomeDoCarro) {
        return carroService.buscarCarro(nomeDoCarro);
    }

    @PutMapping("/{nomeDoCarro}")
    @ResponseStatus(HttpStatus.OK)
    public CarroDTO atualizarCarro(@PathVariable String nomeDoCarro, @RequestBody CarroDTO carroDTO) {
       return carroService.atualizarCarro(nomeDoCarro,carroDTO);
    }

    @DeleteMapping("/{nomeDoCarro}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCarro(@PathVariable String nomeDoCarro){
        carroService.deletarCarro(nomeDoCarro);
    }

}
