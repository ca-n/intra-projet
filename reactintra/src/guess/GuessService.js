const url = 'http://localhost:11011/guess'
const GuessService = {
    addGuess: async (guess) => {
        const res = await fetch(url,
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(guess)
            })
            return await res.json()
    },

    getGuesses: async () => {
        const res = await fetch(url)
        if (res.ok) {
            return await res.json()
        } else {
            console.error(`Error ${res.status}: ${res.text()}`)
        }
    }
}

export default GuessService