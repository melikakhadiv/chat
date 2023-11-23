package com.chat.model.entity;



import com.chat.model.entity.enums.FileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder


@Entity(name="attachmentEntity")
@Table(name="chat_attachment_tbl")

public class Attachment extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="a_title",length = 30)
//    @Pattern(regexp = "^[A-Za-z\\s]{2,30}$", message = "Invalid Role")
    private String title;

//    @Enumerated(EnumType.ORDINAL)
//    @Column(name="a_file_type")
//    private FileType fileType;

    @Column(name="a_file_path")
    private String filePath;
}