package br.com.zup.ZupCar.carro.dtos;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    private List<CarroDTO> concessionaria = new ArrayList<>();

    public List<CarroDTO> pegarTodosOsCarros() {
        return concessionaria;
    }

    public void salvarCarro(CarroDTO carroDTO) {
        concessionaria.add(carroDTO);
    }

    public CarroDTO buscarCarro(String nomeDoCarro) {
        //Forma Elegante
        //O OPTIONAL serve para evitar o NullPointerException
        Optional<CarroDTO> optionalCarroDTO = concessionaria.stream()
                .filter(carro -> carro.getNome().equalsIgnoreCase(nomeDoCarro)).findFirst();

        if (optionalCarroDTO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não Encontrado");
        }
        return optionalCarroDTO.get();

    }

    public CarroDTO atualizarCarro(String nomeDoCarro, CarroDTO carroDTO){
        CarroDTO carro = buscarCarro(nomeDoCarro);
        carro.setAno(carro.getAno());
        carro.setCor(carro.getCor());
        carro.setMotor(carro.getMotor());

        return carro;
    }

    public void deletarCarro(@PathVariable String nomeDoCarro) {
        concessionaria.removeIf(referencia -> referencia.getNome().equalsIgnoreCase(nomeDoCarro));
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado");
    }
}
