package com.example.dgpayproject.base;

import lombok.*;
import org.hibernate.annotations.*;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {
    @Version
    private Integer version;

    @CreationTimestamp
    private Date CreatedDate;

    @UpdateTimestamp
    private Date lastModifiedDate;
}
