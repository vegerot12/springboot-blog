package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//@Data

/*
Here we use modelmapper and it uses a toString , there is toString in DATA annotation as well which causus infinite loop so we remove @data annotation and instead use geetter and setter annotation
 */
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long id;

    @Column(name = "title", nullable = false)
    private  String title;

    @Column(name = "description" , nullable = false)
    private String description;

    @Column(name = "content" , nullable = false)
    private String content;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
     private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;



}
