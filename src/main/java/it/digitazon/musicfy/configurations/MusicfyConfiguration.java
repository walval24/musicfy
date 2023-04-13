package it.digitazon.musicfy.configurations;

import it.digitazon.musicfy.persistence.entities.Artist;
import it.digitazon.musicfy.presentation.dto.ArtistDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MusicfyConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        // Creo l'istanza del model mapper che verrà restituita ai controller in autowired
        ModelMapper modelMapper = new ModelMapper();

        // Inizializza il property mapper per configurare il map di propietà "personalizzate"
        TypeMap<Artist, ArtistDTO> propertyMapper = modelMapper.createTypeMap(Artist.class, ArtistDTO.class);

        // Definisco il mapping, passando al metodo addMapping
        // - il primo parametro il metodo "get.." della sorgente (Artist::getBirthDate)
        // - il secondo parametro il metodo "set.." della destinazione (ArtistDTO::convertDateToString)
        propertyMapper.addMapping(Artist::getBirthDate, ArtistDTO::convertDateToString);


        TypeMap<ArtistDTO,Artist> propertyMapperDTO = modelMapper.createTypeMap(ArtistDTO.class,Artist.class);
        propertyMapperDTO.addMapping(ArtistDTO::convertBirthDate,Artist::setBirthDate);





        // restituisco il modelMapper configurato
        return modelMapper;
    }
}
