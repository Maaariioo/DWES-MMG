using VideojuegosAPI.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace VideojuegosAPI.Repositories
{
    public interface IVideojuegoRepository
    {
        Task<IEnumerable<Videojuego>> GetAll();
        Task<Videojuego> GetById(int id);
        Task Add(Videojuego videojuego);
        Task Update(Videojuego videojuego);
        Task Delete(int id);
    }
}
