type BookApi = {
    bookId: number,
    editionId: number,
    originalTitle: string,
    editionTitle: string,
    description: string,
    publicationYear: number,
    pages: number,
    link: string,
    cover: string,
    languageName: string,
    languageCode: string,
    authors: {
        id: number,
        name: string,
    }[],
    contributors: {
        id: number,
        name: string,
        role: string
    }[]
}

export type { BookApi }
