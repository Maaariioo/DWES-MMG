using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;
using VideojuegosAPI.Models;
using VideojuegosAPI.Repositories;

namespace VideojuegosAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class VideojuegosController : ControllerBase
    {
        private readonly IVideojuegoRepository _repository;

        public VideojuegosController(IVideojuegoRepository repository)
        {
            _repository = repository;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Videojuego>>> GetAll()
        {
            return Ok(await _repository.GetAll());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Videojuego>> GetById(int id)
        {
            var videojuego = await _repository.GetById(id);
            if (videojuego == null) return NotFound();
            return Ok(videojuego);
        }

        [HttpPost]
        public async Task<ActionResult> Create(Videojuego videojuego)
        {
            await _repository.Add(videojuego);
            return CreatedAtAction(nameof(GetById), new { id = videojuego.Id }, videojuego);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> Update(int id, Videojuego videojuego)
        {
            if (id != videojuego.Id) return BadRequest();
            await _repository.Update(videojuego);
            return NoContent();
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> Delete(int id)
        {
            await _repository.Delete(id);
            return NoContent();
        }
    }
}
