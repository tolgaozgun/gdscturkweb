package com.tolgaozgun.gdscturkweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "googlers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GooglerEntity extends UserEntity {


}
