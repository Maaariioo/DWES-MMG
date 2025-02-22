using Microsoft.EntityFrameworkCore;
using VideojuegosAPI.Models;

namespace VideojuegosAPI.Data
{
    public class VideojuegosContext : DbContext
    {
        public VideojuegosContext(DbContextOptions<VideojuegosContext> options) : base(options) { }

        public DbSet<Videojuego> Videojuegos { get; set; }
    }
}
