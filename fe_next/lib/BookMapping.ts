import { BookApi } from "@/types/Api";
import { Book } from "@/types/Book";

function mapBookApi(data: BookApi): Book {
    console.log("This is the book api mapping");
    console.log(data);



    return ({
        id: String(data.editionId),
        bookId: String(data.bookId),
        title: data.editionTitle,
        originalTitle: data.originalTitle,
        description: data.description,
        year: data.publicationYear,
        pages: data.pages,
        link: data.link,
        cover: data.cover,
        language: {
            name: data.languageName,
            code: data.languageCode
        },
        authors: data.authors.map(author => ({
            id: String(author.id),
            name: author.name,
        })),
        contributors: data.contributors.map(contributor => ({
            id: String(contributor.id),
            name: contributor.name,
            role: contributor.role
        })),
    })
}

export { mapBookApi }
