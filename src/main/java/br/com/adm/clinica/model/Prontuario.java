/*
 * package br.com.adm.clinica.model;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.OneToOne; import javax.persistence.SequenceGenerator;
 * import javax.persistence.Table;
 * 
 * @Entity
 * 
 * @Table(name = "adm_prontuario") public class Prontuario {
 * 
 * @Id
 * 
 * @SequenceGenerator(name = "prontuario_GENERATION", sequenceName =
 * "prontuario_id_seq", allocationSize = 1)
 * 
 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
 * "prontuario_GENERATION")
 * 
 * @Column(name = "prontuario_id") private Long id;
 * 
 * @OneToOne
 * 
 * @JoinColumn(name = "paciente_id") private Paciente paciente;
 * 
 * 
 * public Long getId() { return id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * public Paciente getPaciente() { return paciente; }
 * 
 * public void setPaciente(Paciente paciente) { this.paciente = paciente; }
 * 
 * }
 */