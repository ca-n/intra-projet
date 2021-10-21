import React from 'react'
import { useState, useEffect } from 'react'
import GuessService from './GuessService'
import './Guess.css'

const Guess = () => {
    const [guessList, setGuessList] = useState([])
    const [lastGuess, setLastGuess] = useState()
    const [name, setName] = useState("")
    const [guess, setGuess] = useState(0)

    useEffect(() => {
        const getGuessList = async () => {
            const guesses = await GuessService.getGuesses()
            setGuessList(guesses)
        }
        getGuessList()
    }, [])

    const submitGuess = async (e) => {
        e.preventDefault()
        if (!name) {
            alert("SVP entrer un nom")
            return
        }

        if (!guess) {
            alert("SVP entrer un guess")
            return
        }

        const actual = Math.floor(Math.random() * 6) + 1

        const returned = await GuessService.addGuess({name, guess, actual})
        setGuessList([...guessList, returned])
        setLastGuess(returned)
    }

    return (
        <div className="wide">
            <h1>Examen Intra</h1>
            <form onSubmit={submitGuess} className="wide">
                <div>
                    <label>Nom</label>
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
                </div>
                <div>
                    <label>Guess</label>
                    <input type="text" value={guess} onChange={(e) => setGuess(e.target.value)} />
                </div>
                <input type="submit" value="Lance le dé"/>
            </form>
            {lastGuess ?
                <>
                    <h1>{lastGuess.actual}</h1>
                    {lastGuess.guess === lastGuess.actual ? <p>Bravo!</p> : <p>Manqué</p>}
                </> : ''}
            <table className="wide">
                <thead>
                    <tr>
                        <th className="lessWide">Nom</th>
                        <th className="lessWide">Guess</th>
                        <th className="lessWide">Numero Random</th>
                    </tr>
                </thead>
                <tbody>
                    {guessList.length > 0 ? guessList.map(guess =>
                        <tr key={guess.id}>
                            <td className="lessWide">{guess.name}</td>
                            <td className="lessWide">{guess.guess}</td>
                            <td className="lessWide">{guess.actual}</td>
                        </tr>) : <tr><td colSpan='3' className="wide">Aucun guess a afficher</td></tr>}
                </tbody>
            </table>
        </div>
    )
}

export default Guess
