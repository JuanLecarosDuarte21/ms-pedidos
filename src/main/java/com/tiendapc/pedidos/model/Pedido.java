
package com.tiendapc.pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser positivo")
    @Column(nullable = false)
    private Long usuarioId;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(
        value = "0.0",
        inclusive = false,
        message = "El total debe ser mayor a cero"
    )
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fechaPedido;

    @Valid
    @OneToMany(
        mappedBy = "pedido",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<DetallePedido> detalles = new ArrayList<>();

    public Pedido() {
    }

    @PrePersist
    public void antesDeGuardar() {
        if (fechaPedido == null) {
            fechaPedido = LocalDateTime.now();
        }

        if (estado == null || estado.isBlank()) {
            estado = "PENDIENTE";
        }

        sincronizarDetalles();
    }

    @PreUpdate
    public void antesDeActualizar() {
        sincronizarDetalles();
    }

    private void sincronizarDetalles() {
        if (detalles != null) {
            for (DetallePedido detalle : detalles) {
                detalle.setPedido(this);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles != null
                ? detalles
                : new ArrayList<>();

        sincronizarDetalles();
    }
}
