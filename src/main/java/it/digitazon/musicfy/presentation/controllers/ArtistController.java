package it.digitazon.musicfy.presentation.controllers;

import it.digitazon.musicfy.persistence.entities.Artist;
import it.digitazon.musicfy.persistence.entities.Song;
import it.digitazon.musicfy.presentation.dto.ArtistDTO;
import it.digitazon.musicfy.presentation.dto.SongDTO;
import it.digitazon.musicfy.services.ArtistService;
import it.digitazon.musicfy.services.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtistController {

    @Autowired
    private ArtistService artistService;
    @Autowired
    private SongService songService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public List<ArtistDTO> getArtists() {
        return artistService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ArtistDTO getArtistById(@PathVariable long id) {
        return convertToDTO(artistService.getById(id));
    }

    @PostMapping
    public ArtistDTO createArtist(@RequestBody ArtistDTO newArtist) {
        Artist artist = convertToEntity(newArtist);

        artist = artistService.create(artist);

        return convertToDTO(artist);
    }

    @PutMapping("/{id}")
    public ArtistDTO updateArtist(@PathVariable long id, @RequestBody ArtistDTO updateArtist) {
        Artist artist = convertToEntity(updateArtist);
        artist = artistService.update(id,artist);


        return convertToDTO(artist);
    }
    @DeleteMapping ("/{id}")
    public ArtistDTO deleteArtist(@PathVariable long id){
        Artist artist = artistService.getById(id);

        artist.getSongs()
                .forEach(song -> songService.delete(song.getId()));

        return convertToDTO(artistService.delete(id));
    }

    @DeleteMapping ("/{id}/songs")
    public List<SongDTO> deleteSongs(@PathVariable long id) {
        Artist artist = artistService.getById(id);

        return artist.getSongs()
                .stream()
                .map(song -> convertToSongDTO(songService.delete(song.getId())))
                .toList();
    }


     @GetMapping("/{id}/songs")
     public List<SongDTO> getSongs(@PathVariable long id){
        Artist artist = artistService.getById(id);

        return artist.getSongs()
                .stream()
                .map(this::convertToSongDTO)
                .toList();
     }

    private ArtistDTO convertToDTO(Artist artist) {
        return modelMapper.map(artist, ArtistDTO.class);
    }

    private Artist convertToEntity(ArtistDTO dto) {
        return modelMapper.map(dto, Artist.class);

    }

    private SongDTO convertToSongDTO(Song song) {
        return modelMapper.map(song, SongDTO.class);
    }

}
