package it.digitazon.musicfy.persistence.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "type") // riferisce a riga 25 della tab songs
    private List<Song> songs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
