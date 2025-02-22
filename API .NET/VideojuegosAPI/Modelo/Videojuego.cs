using System.ComponentModel.DataAnnotations;

namespace VideojuegosAPI.Models
{
    public class Videojuego
    {
        [Key]
        public int Id { get; set; }

        [Required, MaxLength(100)]
        public string Nombre { get; set; }

        [Required]
        public string Genero { get; set; }

        [Required]
        public decimal Precio { get; set; }

        public int AñoLanzamiento { get; set; }
    }
}
