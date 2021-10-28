package br.com.zup.ZupCar.controller;

import br.com.zup.ZupCar.controller.dtos.CarroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {
    private List<CarroDTO> concessionaria = new ArrayList<>();

    @GetMapping
    public List<CarroDTO> exibirTodosOsCarros() {
        return concessionaria;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCarro(@RequestBody CarroDTO carroDTO) {
        concessionaria.add(carroDTO);
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
        //Forma Elegante
        //O OPTIONAL serve para evitar o NullPointerException
        Optional<CarroDTO> optionalCarroDTO = concessionaria.stream()
                .filter(carro -> carro.getNome().equalsIgnoreCase(nomeDoCarro)).findFirst();

        if (optionalCarroDTO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não Encontrado");
        }
        return optionalCarroDTO.get();

        //Forma Menos Elegante
        /*for(CarroDTO objeto: concessionaria){
            if(objeto.getModelo().equals(nomDoCarro)){
                return objeto;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não Encontrado");
        */
    }

    @PutMapping("/{nomeDoCarro}")
    @ResponseStatus(HttpStatus.OK)
    public CarroDTO atualizarCarro(@PathVariable String nomeDoCarro, @RequestBody CarroDTO carroDTO) {
        CarroDTO carroObjeto = null;
        for (CarroDTO objeto : concessionaria) {
            if (objeto.getNome().equals(nomeDoCarro)) {
                carroObjeto.setNome(carroDTO.getNome());
                carroObjeto.setModelo(carroDTO.getModelo());
                carroObjeto.setAno(carroDTO.getAno());
                carroObjeto.setCor(carroDTO.getCor());
                carroObjeto.setMotor(carroDTO.getMotor());
                return carroObjeto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não Encontrado");
    }

    @DeleteMapping("/{nomeDoCarro}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCarro(@PathVariable String nomeDoCarro) {
        List<CarroDTO> removerCarro = new ArrayList<>();
        for (CarroDTO referencia : concessionaria) {
            if (referencia.getNome().equalsIgnoreCase(nomeDoCarro)) {
                concessionaria.remove(referencia);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado");
        }
    }

}
