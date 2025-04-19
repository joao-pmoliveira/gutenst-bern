type Book = {
    id: string,
    bookId: string,
    title: string,
    originalTitle: string,
    description: string,
    link: string,
    cover: string,
    year: string,
    language: { name: string, code: string },
    authors: Author[],
    contributors: Contributor[]
}

type Author = {
    id: string,
    name: string,
}

type Contributor = {
    id: string,
    name: string,
    role: string,
}

export type { Book, Author, Contributor }
