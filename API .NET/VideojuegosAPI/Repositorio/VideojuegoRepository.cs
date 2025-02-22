using Microsoft.EntityFrameworkCore;
using VideojuegosAPI.Data;
using VideojuegosAPI.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace VideojuegosAPI.Repositories
{
    public class VideojuegoRepository : IVideojuegoRepository
    {
        private readonly VideojuegosContext _context;

        public VideojuegoRepository(VideojuegosContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Videojuego>> GetAll()
        {
            return await _context.Videojuegos.ToListAsync();
        }

        public async Task<Videojuego> GetById(int id)
        {
            return await _context.Videojuegos.FindAsync(id);
        }

        public async Task Add(Videojuego videojuego)
        {
            _context.Videojuegos.Add(videojuego);
            await _context.SaveChangesAsync();
        }

        public async Task Update(Videojuego videojuego)
        {
            _context.Videojuegos.Update(videojuego);
            await _context.SaveChangesAsync();
        }

        public async Task Delete(int id)
        {
            var videojuego = await _context.Videojuegos.FindAsync(id);
            if (videojuego != null)
            {
                _context.Videojuegos.Remove(videojuego);
                await _context.SaveChangesAsync();
            }
        }
    }
}
