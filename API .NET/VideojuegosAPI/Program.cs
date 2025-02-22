using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.EntityFrameworkCore;
using VideojuegosAPI.Data;
using VideojuegosAPI.Models;
using VideojuegosAPI.Repositories;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<VideojuegosContext>(options =>
    options.UseInMemoryDatabase("VideojuegosDB"));

builder.Services.AddScoped<IVideojuegoRepository, VideojuegoRepository>();

var app = builder.Build();


using (var scope = app.Services.CreateScope())
{
    var context = scope.ServiceProvider.GetRequiredService<VideojuegosContext>();
    context.Videojuegos.AddRange(
        new Videojuego { Id = 1, Nombre = "The Witcher 3", Genero = "RPG", Precio = 29.99m, AñoLanzamiento = 2015 },
        new Videojuego { Id = 2, Nombre = "Cyberpunk 2077", Genero = "Acción", Precio = 49.99m, AñoLanzamiento = 2020 },
        new Videojuego { Id = 3, Nombre = "Dark Souls III", Genero = "RPG", Precio = 39.99m, AñoLanzamiento = 2016 },
        new Videojuego { Id = 4, Nombre = "Jak 3", Genero = "Plataformas", Precio = 19.99m, AñoLanzamiento = 2004 },
        new Videojuego { Id = 5, Nombre = "Super Mario Bros", Genero = "Plataformas", Precio = 9.99m, AñoLanzamiento = 1985 },
        new Videojuego { Id = 6, Nombre = "The Last of Us", Genero = "Acción-Aventura", Precio = 39.99m, AñoLanzamiento = 2013 },
        new Videojuego { Id = 7, Nombre = "Grand Theft Auto: San Andreas", Genero = "Acción", Precio = 14.99m, AñoLanzamiento = 2004 },
        new Videojuego { Id = 8, Nombre = "The Legend of Zelda: Ocarina of Time", Genero = "Aventura", Precio = 19.99m, AñoLanzamiento = 1998 },
        new Videojuego { Id = 9, Nombre = "Final Fantasy VII", Genero = "RPG", Precio = 15.99m, AñoLanzamiento = 1997 },
        new Videojuego { Id = 10, Nombre = "Halo: Combat Evolved", Genero = "FPS", Precio = 14.99m, AñoLanzamiento = 2001 }
    );
    context.SaveChanges();
}

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();
app.Run();
