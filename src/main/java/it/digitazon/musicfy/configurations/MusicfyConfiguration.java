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
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();


        TypeMap<Artist, ArtistDTO> proprtyMapper = modelMapper.createTypeMap(Artist.class,ArtistDTO.class);
        proprtyMapper.addMapping(Artist::getBirthDate,ArtistDTO::convertDateToString);

        return new ModelMapper();
    }
}
