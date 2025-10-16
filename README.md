# Runner — 2D Platformer

A lightweight 2D **runner/platformer** written in Java. Dodge spikes, outsmart ghosts, and survive as long as you can. Built around a simple game loop with clean separation of rendering, input, and gameplay systems.

---

## ✨ Features

- Tight platforming controls (jump, mid‑air control, coyote time configurable)
- Hazards (`Spikes`) and enemies (`Ghost`) with central organizers for updates and collisions
- Level loader/manager with restart & progression hooks
- Pause/Resume, Restart, and simple on‑screen UI buttons
- FPS‑based update/render loop in `GamePanel`
- Minimal dependencies (plain Java + Swing/AWT)

---

## 🎮 Gameplay & Controls

- **Move**: `A / D` or `← / →`  
- **Jump**: `W` or `↑` or `Space`  
- **Restart Level**: `R`  
- **Pause/Resume**: `P` or click the UI button  
- **Exit**: `Esc` (or close window)

> Tip: You can tweak physics constants (speed, gravity, jump impulse) in `Player` if exposed as fields or config.

---

## 🧱 Code Architecture (Key Classes)

The game is organized into small, focused classes:

- `Game` — Application entry point. Creates the window, sets up the main `GamePanel`, and starts the loop.
- `GamePanel` — The core **game loop** (update/render), input listeners, and high‑level state (menu, playing, paused).
- `Level` — Level data & helpers (tiles, spawn points, reset hooks, win/loss conditions).
- `Player` — Player state & physics (position, velocity, collisions, jump logic).
- `Spikes` — A hazard entity (damage on touch).
- `SpikesOrganizer` — Manages all spikes: spawn, update, collision checks with `Player`.
- `Ghost` — An enemy entity (e.g., patrol/chase logic).
- `GhostOrganizer` — Manages all ghosts: AI updates and collisions with `Player`.
- `Button1`, `Button2` — Simple UI buttons (menu/pause/restart, etc.).

> Collision and entity updates are centralized in the organizer classes to keep `GamePanel` lean and maintainable.
