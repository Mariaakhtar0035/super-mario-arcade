# Super Mario Bros Arcade

A text-based arcade of Mario-themed minigames, built in Java for my Grade 12 Computer Science culminating project (May–June 2023). It's inspired by the minigames from *New Super Mario Bros.* on the Nintendo DS, a game I played all the time growing up.

This is a project from high school that I'm still really proud of. It was the first time I built something big that people actually wanted to play, and it's a big part of what made me choose a career in software.

I'm keeping it here as a time capsule from when I was first learning to code. The code is preserved as-is, comments and all.

## The games

| # | Game | Description |
|---|------|-------------|
| 1 | **Whack-A-Goomba** | Whack as many Goombas as you can before time runs out. High scores are saved. |
| 2 | **Mario's Dilemma** | A choose-your-own-adventure through the Mushroom Forest, Warp Pipe Maze, and Haunted Mansion. |
| 3 | **Phrase Frenzy** | Make as many words as you can from the letters in "Super Mario Bros" in 45 seconds. High scores are saved. |
| 4 | **Luigi's Casino – Blackjack** | Play blackjack against the dealer. |
| 5 | **Bowser's Tic-Tac Takeover** | Two-player tic-tac-toe. |

Each game has its own music and sound effects.

## Running it

Requires Java 8 or newer. Run it **from the project root** so it can find the `Music/`, `SoundEffects/`, and high-score files.

```bash
javac -d build src/supermario/*.java
java -cp build supermario.SuperMario
```

## Credits

Made by **Maria Akhtar** and **Ruchir Lalwani**.

*Mario and all related characters and music belong to Nintendo. This is a non-commercial fan project.*
