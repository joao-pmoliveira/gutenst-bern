export default function SearchBar() {
    return (
        <div className="
            flex items-center bg-gray-100
            rounded-xl
            basis-1/3
            px-2
        ">
            <i className="
                shrink-0 text-base text-gray-500
                select-none
            ">
                O
            </i>
            <input
                id="search" name="search"
                placeholder="Search Book"
                className="
                    py-1.5 pr-3 pl-2
                    text-base text-gray-900
                    placeholder:text-gray-400
                    focus:outline-none
                "
            />
        </div>
    )
}
